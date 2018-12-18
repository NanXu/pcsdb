package cn.edu.cauc.controller.event;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cauc.controller.base.BaseController;
import cn.edu.cauc.model.po.event.Casualties;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.po.event.EventView;
import cn.edu.cauc.model.po.event.Measures;
import cn.edu.cauc.model.po.event.Plane;
import cn.edu.cauc.model.po.event.Pollutant;
import cn.edu.cauc.model.vo.EventForm;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.SessionUser;
import cn.edu.cauc.service.event.IEventService;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.JacksonUtil;

/**
 * 作者： 徐楠
 *
 * 描述：<p>TODO</p>
 * 创建时间：2016年10月7日
 */
@Controller
@RequestMapping("/event/draft")
public class EventDraftController extends BaseController {
	
	@Resource
	private IEventService eventService;

	@RequestMapping(value="/listEvents")
	public ModelAndView listEvents(@ModelAttribute EventInfo event, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		SessionUser sessionUser = (SessionUser)getRequest().getSession().getAttribute(Constant.SESSION_USER);
		event.setDraftUser(sessionUser.getUsername());
		Page<EventInfo> page = eventService.findDraftEventList(event,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("event/draft/event_draft_list");
		return mv;
	}
	
	@RequestMapping(value="/toDraftEvent")
	public ModelAndView toDraftEvent() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("event/draft/event_draft");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/addEvent", method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String addEvent(@ModelAttribute("eventAddForm") EventForm eventForm) {
		try {
			if(eventForm != null) {
				EventInfo event = eventForm.getEvent();
				if(event != null) {
					SessionUser sessionUser = (SessionUser)getRequest().getSession().getAttribute(Constant.SESSION_USER);
					event.setDraftUser(sessionUser.getUsername());
					event.setDraftTime(new Date());
					event.setStatus(Constant.EVENT_DRAFT);//起草状态
					Integer eventId = eventService.saveEventInfo(event);
					Plane plane = eventForm.getPlane();
					if(plane != null) {
						plane.setEventId(eventId);
						eventService.savePlane(plane);
					}
					Casualties casualties = eventForm.getCasualties();
					if(casualties != null) {
						casualties.setEventId(eventId);
						eventService.saveCasualties(casualties);
					}
					Pollutant pollutant = eventForm.getPollutant();
					if(pollutant != null) {
						pollutant.setEventId(eventId);
						eventService.savePollutant(pollutant);
					}
					Measures measures = eventForm.getMeasures();
					if(measures != null) {
						measures.setEventId(eventId);
						eventService.saveMeasures(measures);
					}
				}
			}
			return JacksonUtil.ajaxJson(true, "添加事件成功");
		} catch (Exception e) {
			logger.debug("添加会员失败", e);
			return JacksonUtil.ajaxJson(false, "添加事件失败");
		}
	}
	
	@RequestMapping(value="/detailEvents/{id}")
	public ModelAndView detailEvents(@PathVariable Integer id) {
		ModelAndView mv = this.getModelAndView();
		EventInfo event = eventService.findEventById(id);
		Plane plane = eventService.findPlaneByEventId(id);
		Casualties casualties = eventService.findCasualtiesByEventId(id);
		Measures measures = eventService.findMeasuresByEventId(id);
		Pollutant pollutant = eventService.findPollutantByEventId(id);
		mv.addObject("event", event);
		mv.addObject("plane", plane);
		mv.addObject("casualties", casualties);
		mv.addObject("measures", measures);
		mv.addObject("pollutant", pollutant);
		mv.setViewName("event/draft/event_draft_detail");
		return mv;
	}
	
	@RequestMapping(value="/editEvent/{id}")
	public ModelAndView editEvent(@PathVariable Integer id) {
		ModelAndView mv = this.getModelAndView();
		EventView eventView = eventService.findEventViewById(id);
		mv.addObject("eventView", eventView);
		mv.setViewName("event/draft/event_edit");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/editEvent", method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String editEvent(@ModelAttribute("editDraftEventForm") EventForm eventForm) {
		try {
			if(eventForm != null) {
				EventInfo event = eventForm.getEvent();
				if(event != null) {
					SessionUser sessionUser = (SessionUser)getRequest().getSession().getAttribute(Constant.SESSION_USER);
					event.setDraftUser(sessionUser.getUsername());
					event.setDraftTime(new Date());
					event.setStatus(Constant.EVENT_DRAFT);//起草状态
					Integer eventID = event.getId();
					EventInfo eInfo = eventService.findEventById(eventID);
					BeanUtils.copyProperties(event, eInfo);
					eventService.modifyEventInfo(eInfo);
					
					//Integer eventId = eventService.saveEventInfo(event);
					Plane plane = eventForm.getPlane();
					if(plane != null) {
						plane.setEventId(eventID);
						Plane p = eventService.findPlaneByEventId(eventID);
						if(p != null) {
							BeanUtils.copyProperties(plane, p, new String[]{"id"});
							eventService.modifyPlane(p);
						} else {
							eventService.savePlane(plane);
						}
					}
					Casualties casualties = eventForm.getCasualties();
					if(casualties != null) {
						casualties.setEventId(eventID);
						Casualties c = eventService.findCasualtiesByEventId(eventID);
						if(c != null) {
							BeanUtils.copyProperties(casualties, c, new String[]{"id"});
							eventService.modifyCasualties(c);
						} else {
							eventService.saveCasualties(casualties);
						}
					}
					Pollutant pollutant = eventForm.getPollutant();
					if(pollutant != null) {
						pollutant.setEventId(eventID);
						Pollutant po = eventService.findPollutantByEventId(eventID);
						if(po != null) {
							BeanUtils.copyProperties(pollutant, po, new String[]{"id"});
							eventService.modifyPollutant(po);
						} else {
							eventService.savePollutant(pollutant);
						}
					}
					Measures measures = eventForm.getMeasures();
					if(measures != null) {
						measures.setEventId(eventID);
						Measures m = eventService.findMeasuresByEventId(eventID);
						if(m != null) {
							BeanUtils.copyProperties(measures, m, new String[]{"id"});
							eventService.modifyMeasures(m);
						} else {
							eventService.saveMeasures(measures);
						}
					}
				}
			}
			return JacksonUtil.ajaxJson(true, "编辑事故成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("编辑事故失败", e);
			return JacksonUtil.ajaxJson(false, "编辑事故失败");
		}
	}
}
