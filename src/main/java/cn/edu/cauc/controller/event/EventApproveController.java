package cn.edu.cauc.controller.event;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cauc.controller.base.BaseController;
import cn.edu.cauc.model.po.event.ApproveInfo;
import cn.edu.cauc.model.po.event.Casualties;
import cn.edu.cauc.model.po.event.EventInfo;
import cn.edu.cauc.model.po.event.Measures;
import cn.edu.cauc.model.po.event.Plane;
import cn.edu.cauc.model.po.event.Pollutant;
import cn.edu.cauc.model.vo.Page;
import cn.edu.cauc.model.vo.SessionUser;
import cn.edu.cauc.service.event.IApproveService;
import cn.edu.cauc.service.event.IEventService;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.JacksonUtil;

/**
 * 作者： 徐楠
 *
 * 描述：<p>事故审批</p>
 * 创建时间：2016年10月7日
 */
@Controller
@RequestMapping("/event/approve")
public class EventApproveController extends BaseController {
	
	@Resource
	private IEventService eventService;
	@Resource
	private IApproveService approveService;
	
	@RequestMapping(value="/listEvents")
	public ModelAndView listEvents(@ModelAttribute EventInfo event, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<EventInfo> page = eventService.findDraftEventList(event,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("event/approve/event_approve_list");
		return mv;
	}
	
	@RequestMapping(value="/listApproves")
	public ModelAndView listApproves(@ModelAttribute ApproveInfo approveInfo, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		Page<ApproveInfo> page = approveService.findApproveList(approveInfo,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("event/approve/approve_info_list");
		return mv;
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
		mv.setViewName("event/approve/event_approve_detail");
		return mv;
	}
	
	@RequestMapping(value="/toApproveEvent/{id}")
	public ModelAndView toApproveEvent(@PathVariable Integer id) {
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
		mv.setViewName("event/approve/event_approve");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/approveEvent", method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String approveEvent(@ModelAttribute ApproveInfo approveInfo) {
		try {
			if(approveInfo != null) {
				SessionUser sessionUser = (SessionUser)getRequest().getSession().getAttribute(Constant.SESSION_USER);
				approveInfo.setApprover(sessionUser.getUsername());
				approveInfo.setApproveDate(new Date());
				eventService.approveEvent(approveInfo);
				//更新Event状态
				EventInfo eventInfo = eventService.findEventById(approveInfo.getEventId());
				if(eventInfo != null) {
					if("1".equals(approveInfo.getPass())) {//审批通过
						eventInfo.setStatus(Constant.EVENT_PUBLISH);
					} else {//审批未通过
						eventInfo.setStatus(Constant.EVENT_UNPASS);
					}
					eventService.modifyEventInfo(eventInfo);
				}
			}
			return JacksonUtil.ajaxJson(true, "审批事故信息成功");
		} catch (Exception e) {
			logger.debug("审批事故信息失败", e);
			return JacksonUtil.ajaxJson(false, "审批事故信息失败");
		}
	}
	
	@RequestMapping(value="/approveDetail/{remark}")
	public ModelAndView toApproveDetail(@PathVariable String remark) {
		ModelAndView mv = this.getModelAndView();
		mv.addObject("remark", remark);
		mv.setViewName("event/approve/approve_detail");
		return mv;
	}
}
