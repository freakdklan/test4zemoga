package com.zemoga.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zemoga.web.data.Portfolio;
import com.zemoga.web.service.PortfolioWebService;

@RestController
@RequestMapping(value = "/application")
public class PortfolioWebController {
	
	@Autowired
	private PortfolioWebService portfolioWebService;
	
	@RequestMapping(value = "/getportfolio/{id}", method = RequestMethod.GET)
	public ResponseEntity<Portfolio> getPortfolio(@PathVariable Integer id) {
		return portfolioWebService.getPortfolio(id);
	}
	
	@RequestMapping(value = "/gettweets/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Tweet>> getTweets(@PathVariable Integer id) {
		return portfolioWebService.getTweets(id);
	}
	
	@PostMapping("/updateportfolio/{id}")
	public ResponseEntity<?> updatePortfolio(@RequestBody Portfolio updatedPortfolio, @PathVariable Integer id) {
	    return portfolioWebService.updatePortfolio(id, updatedPortfolio);    
	}
	
}
