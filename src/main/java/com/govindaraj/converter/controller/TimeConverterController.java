package com.govindaraj.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.govindaraj.converter.service.TimeControllerService;

@RestController

@RequestMapping("/time")
public class TimeConverterController {

	@Autowired
	TimeControllerService service;

	@GetMapping(path="/convert/{time}",produces = MediaType.APPLICATION_JSON_VALUE)

	public String convertTimeToString(@PathVariable("time") String timeString) {

		return service.convertTimeToWords(timeString);

	}


}
