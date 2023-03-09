package com.govindaraj.converter.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TimeControllerService {

	Logger logger = LoggerFactory.getLogger(TimeControllerService.class);

	public  String convertTimeToWords(String time) {
		// parse the time using the LocalTime class and a DateTimeFormatter

		try {
			LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));


			if (localTime.equals(LocalTime.MIDNIGHT)) {
				return "It's Midnight";
			} else if (localTime.equals(LocalTime.NOON)) {
				return "It's Midday";
			}

			int hour = localTime.getHour();
			int minute = localTime.getMinute();

			Locale locale = Locale.US;

			// convert the hour to words
			String hourInWords = hour == 0 ? "twelve" : (hour <= 12 ? convertNumberToWords(hour, locale) : convertNumberToWords(hour - 12, locale));

			// convert the minute to words
			String minuteInWords = convertNumberToWords(minute, locale);

			// build the final string and return it
			return String.format("It's %s %s %s", hourInWords, (minute == 0 ? "" : (minute == 1 ? "oh one" : minuteInWords)), (hour < 12 ? "AM" : "PM"));
		} catch (Exception e) {			
			logger.info("Exception occured message - {}", e.getMessage());
			return "Invalid time format, format should be like hh:mm";
		}
	}

	private  String convertNumberToWords(int number, Locale locale) {
		String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
		String[] tens = {"", "", "twenty", "thirty", "forty", "fifty"};

		if (number < 20) {
			return ones[number];
		} else {
			int tensDigit = number / 10;
			int onesDigit = number % 10;

			if (onesDigit == 0) {
				return tens[tensDigit];
			} else {
				return String.format("%s %s", tens[tensDigit], ones[onesDigit]);
			}
		}
	}


}
