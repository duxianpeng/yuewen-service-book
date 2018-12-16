package com.yuewen.web;

import com.yuewen.Constains;
import com.yuewen.entity.Flight;
import com.yuewen.service.FlightService;
import com.yuewen.service.MessageProducer;
import com.yuewen.vo.MessageEntity;
import com.yuewen.vo.ResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/flight")
public class FlightController {

	private Logger logger = LoggerFactory.getLogger(FlightController.class);
	@Autowired
	private FlightService flightService;

    @Autowired
    private MessageProducer kafkaProducer;

    @Value("${kafka.topic.default}")
    private String topic;

    @ApiOperation(value = "CreateFlightInfo", notes = "Create Flight based on parameter", response = ResponseBody.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseBody saveFlight(Flight flight) {

		Flight self = flightService.save(flight);
		return new ResponseBody(null, "", true);
	}

    @ApiOperation(value = "FindFlightByCategory", notes = "Find Flight info by category", response = ResponseBody.class)
    @RequestMapping(value = "/findByCategory", method = RequestMethod.GET)
	public ResponseBody findByCategory(@RequestParam(value = "category", required = true) String category,
									   @RequestParam(value="page", required = false) String page,
									   @RequestParam(value="page", required = false) String pageSize) {
		int pageInt = -1;
		int pageSizeInt = Integer.MAX_VALUE;

		kafkaProducer.send(topic, new MessageEntity("Flight Info", category));
		try {
			if(!StringUtils.isEmpty(page)){
				pageInt = Integer.parseInt(page);
			}
			if(!StringUtils.isEmpty(pageSize)){
				pageSizeInt = Integer.parseInt(pageSize);
			}
		} catch (NumberFormatException e) {
			logger.info(e.getMessage());
			return new ResponseBody(null, Constains.ERROR_MSG_WRONG_PARAMTER, false);
		}
		Page<Flight> resultInPage = flightService.findByCategory(category, pageInt, pageSizeInt);
		List<Flight> result = resultInPage.getContent();
		long totalElements = resultInPage.getTotalElements();
		int totalPages = resultInPage.getTotalPages();
		return new ResponseBody(result, "", true, pageInt, pageSizeInt,
				totalElements, totalPages);
	}

}
