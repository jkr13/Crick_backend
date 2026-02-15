package com.crickPulse.cron;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.crickPulse.controller.CrickController;
import com.crickPulse.controller.CricketController;
import com.crickPulse.controller.IplController;
import com.crickPulse.controller.LineController;
import com.crickPulse.controller.LiveLineController;
import com.crickPulse.controller.ScoreController;
import com.crickPulse.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;

@EnableScheduling
@Configuration
@EnableAsync
public class TimeScheduler {

	@Scheduled(fixedDelay = Constants.HOUR)
	private void getFixture() throws ParseException, JsonProcessingException, IOException {

		// System.out.println("Getting Fixture ");

//		String url = Constants.ICC_BASEURL + "/fixtures?tournamentIds=" + Constants.TID
//				+ "&startDate=2023-09-28&matchStates=U&matchStates=L&sort=asc&page=0&pageSize=50&tournamentTypes=I&tournamentTypes=WI&altIds=true";
//
//		String response = WebCaller.getResult(url, WebCaller.HTTP_GET, null, Utils.getHeaders());
//
//		ScoreController.responses.put(Constants.FIXTURE, response);

		// System.out.println("Fixture : " + response);

	}

	@Scheduled(fixedDelay = Constants.MIN)
	private void updateCEMaps() throws ParseException, JsonProcessingException, IOException {

		CrickController.getAllPlayers();
		CrickController.getAllSeries();
		CrickController.getAllTeam();
		CrickController.getAllSeriesFormat();
		CrickController.getAllVenue();
		CrickController.getAllUmpire();

	}

	/**
	 * This function make clear response and refresh time map at every day 2.00 a.m.
	 */
	@Scheduled(cron = "0 0 21 * * *")
	private void realizeResponses() {

		ScoreController.clearResponsesMap();
		LiveLineController.clearResponsesMap();
		LineController.clearResponsesMap();
		IplController.clearResponsesMap();
		CrickController.clearResponsesMap();
		CricketController.clearResponsesMap();
	}

}
