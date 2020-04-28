package com.zemoga.web.twitter;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class TwitterConnector {
	
	private static final String CUSTOMER_KEY = "KRy7l0v8wex3w8Sy5zThai3Ea";
	private static final String CUSTOMER_SECRET = "X2eBm0Y21kYEuR74W3Frqc2JVIizOj8Q1EVGatDsEVVEJo0ucu";
	private static final String ACCESS_TOKEN = "1220032047516921859-otvXjhExyUTZ5GLxssc9h5ORqtPZja";
	private static final String ACCESS_TOKEN_SECRET = "tmJKqM4ORfQW6CH7wIVV8uKNpmSEmeFAP8lYwGb19uYjj";
	
	private Twitter twitter;
	
	
	private void initializeTwitterTemplate()
	{
		twitter = new TwitterTemplate(CUSTOMER_KEY, CUSTOMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
	}
	
	public List<Tweet> getTweetsByUserName(String userName)
	{
		if(null == twitter) initializeTwitterTemplate();
		
		return twitter.timelineOperations().getUserTimeline(userName).subList(0, 4);	
	}
	
}
