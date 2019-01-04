package cn.edu.cauc.controller.event;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cauc.service.event.IAidsCsvDataService;
import org.apache.log4j.Logger;
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
import cn.edu.cauc.service.event.IEventService;
import cn.edu.cauc.util.Constant;
import cn.edu.cauc.util.DateUtil;
import cn.edu.cauc.util.JacksonUtil;
import cn.edu.cauc.util.csv.CsvExportUtil;

/**
 * 作者： 徐楠
 *
 * 描述：<p>事故管理相关 </p>
 * 创建时间：2016年10月7日
 */
@Controller
@RequestMapping("/event/info")
public class EventController extends BaseController {

	private static final Logger logger = Logger.getLogger(EventController.class);
	
	@Resource
	private IEventService eventService;
	@Resource
	private IAidsCsvDataService aidsCsvDataService;

	@RequestMapping(value="/addAidsCsvData", method=RequestMethod.POST)
	public void addAidsCsvData() {
		aidsCsvDataService.addEvent();
	}
	
	@RequestMapping(value="/listEvents")
	public ModelAndView listEvents(@ModelAttribute EventView event, 
			@RequestParam(value="pageNo", defaultValue="1") Integer pageNo, 
			@RequestParam(value="pageSize", required = false) Integer pageSize) {
		ModelAndView mv = this.getModelAndView();
		if(pageSize == null) {
			pageSize = Constant.PAGE_SIZE;
		}
		int startPage = this.startPaging(pageNo, pageSize);
		if(event == null) {
			event = new EventView();
		}
		event.setStatus(Constant.EVENT_PUBLISH);
		Page<EventView> page = eventService.findEventList(event,startPage,pageSize);
		mv.addObject("page", page);
		mv.addObject("pageNo", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.setViewName("event/info/event_list");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/exportEventData")
	public String exportEventsData(@ModelAttribute EventView event, HttpServletResponse response) {
		List<Map<String, Object>> eventDataList = null;
		if(event == null) {
			event = new EventView();
		}
		event.setStatus(Constant.EVENT_PUBLISH);
        List<EventView> eventViewList = eventService.findEventViewList(event);// 查询到要导出的信息
        if (eventViewList.size() == 0) {
           return JacksonUtil.ajaxJson(false, "无需要导出的数据");
        }
        String sTitle = "数据来源,源编号,事故类型,当地日期,最后起飞地,目的地,事件发生地,天气状况,飞机损害程度,飞行阶段,事件描述,原因描述,飞机型号,飞机制造商,注册号,发动机制造商,发动机型号,发动机数量,运营商,运行规章,总飞行小时数,维修类型,维修后运行时间,污染物来源,涉及系统,故障位置," +
				"故障模式,预防/紧急措施,其他机组控制措施,维修措施,备注,机组死亡,机组重伤,机组轻伤,乘客死亡,乘客重伤,乘客轻伤,飞行性质,气候因素影响到人,气候因素直接影响飞机系统,存在人为因素";
        String fName = "event_";
        String mapKey = "source,sourceId,eventType,localDate,lastDeparturePoint,destinationLocal,eventLocation,weatherConditions,aircraftDamage,phaseFlight,eventRemarks,reasonRemarks,"
        		+ "aircraftModel,aircraftMake,registrationNumber,engineManufactuer,engineModel,numberOfEngines,operator,runRules,runTotalHours,maintainType,maintainHours,pullutantSource,relateToSystem,"
        		+ "bugLocation,bugModel,prevent,controls,maintain,remark,crewFatal,crewSerious,crewMinor,passengerFatal,passengerSerious,passengerMinor,flightProperties,isWeatherFactor,isAffectAircraftSystem,isArtificialFactor";
        eventDataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (EventView view : eventViewList) {
            map = new HashMap<String, Object>();
            map.put("source", viewNull(view.getSource()));
            map.put("sourceId", viewNull(view.getSourceId()));
            map.put("eventType", viewNull(view.getEventType()));
            map.put("localDate", DateUtil.day2String(view.getLocalDate()));
            map.put("lastDeparturePoint", viewNull(view.getLastDeparturePoint()));
            map.put("destinationLocal", viewNull(view.getDestinationLocal()));
            map.put("eventLocation", viewNull(view.getEventLocation()));
            map.put("weatherConditions", viewNull(view.getWeatherConditions()));
            map.put("aircraftDamage", viewNull(view.getAircraftDamage()));
            map.put("phaseFlight", viewNull(view.getPhaseFlight()));
            map.put("eventRemarks", viewNull(view.getEventRemarks()));
            map.put("reasonRemarks", viewNull(view.getReasonRemarks()));
            map.put("aircraftModel", viewNull(view.getAircraftModel()));
            map.put("aircraftMake", viewNull(view.getAircraftMake()));
            map.put("registrationNumber", viewNull(view.getRegistrationNumber()));
            map.put("engineManufactuer", viewNull(view.getEngineManufactuer()));
            map.put("engineModel", viewNull(view.getEngineModel()));
            map.put("numberOfEngines", viewNull(view.getNumberOfEngines()));
            map.put("operator", viewNull(view.getOperator()));
            map.put("runRules", viewNull(view.getRunRules()));
            map.put("runTotalHours", viewNull(view.getRunTotalHours()));
            map.put("maintainType", viewNull(view.getMaintainType()));
            map.put("maintainHours", viewNull(view.getMaintainHours()));
            map.put("pullutantSource", viewNull(view.getPullutantSource()));
            map.put("relateToSystem", viewNull(view.getRelateToSystem()));
            map.put("bugLocation", viewNull(view.getBugLocation()));
            map.put("bugModel", viewNull(view.getBugModel()));
            map.put("prevent", viewNull(view.getPrevent()));
            map.put("controls", viewNull(view.getControls()));
            map.put("maintain", viewNull(view.getMaintain()));
            map.put("remark", viewNull(view.getRemark()));
            map.put("crewFatal", viewNull(view.getCrewFatal()));
            map.put("crewSerious", viewNull(view.getCrewSerious()));
            map.put("crewMinor", viewNull(view.getCrewMinor()));
            map.put("passengerFatal", viewNull(view.getPassengerFatal()));
            map.put("passengerSerious", viewNull(view.getPassengerSerious()));
            map.put("passengerMinor", viewNull(view.getPassengerMinor()));
			map.put("flightProperties", viewNull(view.getFlightProperties()));
			map.put("isWeatherFactor", viewNull(view.getIsWeatherFactor()));
			map.put("isAffectAircraftSystem", viewNull(view.getIsAffectAircraftSystem()));
			map.put("isArtificialFactor", viewNull(view.getIsArtificialFactor()));
//            map.put("createDate", DateFormatUtils.format(order.getCreateDate(), "yyyy/MM/dd HH:mm"));

            eventDataList.add(map);
        }
        try (final OutputStream os = response.getOutputStream()) {
            CsvExportUtil.responseSetProperties(fName, response);
            CsvExportUtil.doExport(eventDataList, sTitle, mapKey, os);
            return null;

        } catch (Exception e) {
            logger.error("购买CSV失败", e);
        }
		return JacksonUtil.ajaxJson(true, "导出数据成功");
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
		mv.setViewName("event/info/event_detail");
		return mv;
	}
	
	@RequestMapping(value="/editEvent/{id}")
	public ModelAndView editEvent(@PathVariable Integer id) {
		ModelAndView mv = this.getModelAndView();
		EventView eventView = eventService.findEventViewById(id);
		mv.addObject("eventView", eventView);
		mv.setViewName("event/info/event_edit");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/editEvent", method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String editEvent(@ModelAttribute("editEventForm") EventForm eventForm) {
		try {
			if(eventForm != null) {
				EventInfo event = eventForm.getEvent();
				if(event != null) {
//					SessionUser sessionUser = (SessionUser)getRequest().getSession().getAttribute(Constant.SESSION_USER);
//					event.setDraftUser(sessionUser.getUsername());
//					event.setDraftTime(new Date());
//					event.setStatus(Constant.EVENT_DRAFT);//起草状态
					Integer eventID = event.getId();
					EventInfo eInfo = eventService.findEventById(eventID);
					BeanUtils.copyProperties(event, eInfo, new String[]{"draftUser", "draftTime", "status", "approveUser", "approveTime"});
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
	
	@ResponseBody
	@RequestMapping(value="/deleteEvent", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String deleteEvent(@RequestParam(value="deleteIDs") String ids) {
		try {
			eventService.deleteEvents(ids);
			return JacksonUtil.ajaxJson(true);
		} catch (Exception e) {
			logger.debug("删除事故失败", e);
			return JacksonUtil.ajaxJson(false);
		}
	}
	
	/**
	 * 
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value="/editEvent")
//	public String editEvent(@ModelAttribute EventView eventView) {
//		if(eventView != null) {
//			EventView view = eventService.findEventViewById(eventView.getId());
//			BeanUtils.copyProperties(eventView, view);
//			eventService.edit(view);
//			return JacksonUtil.ajaxJson(true);
//		} else {
//			return JacksonUtil.ajaxJson(false);
//		}
//	}
	
	/**
	 * 空对象显示为""
	 * 
	 * @param obj
	 * @return
	 */
	private Object viewNull(Object obj) {
		if(obj == null){
			return "";
		} else {
			if(obj instanceof String) {
				String str = (String)obj;
				String dest = "";
				if (str!=null) {
					Pattern p = Pattern.compile("\\t|\r|\n");
					Matcher m = p.matcher(str);
					dest = m.replaceAll("");
				}
				return dest.replaceAll(",", ".");
			} else {
				return obj;
			}
			
		}
	}
	
}
