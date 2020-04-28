package com.zemoga.web.controller.test;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.test.web.servlet.MockMvc;

import com.zemoga.web.data.Portfolio;
import com.zemoga.web.service.PortfolioWebService;



@SpringBootTest
@AutoConfigureMockMvc
class PortfolioWebServiceTests {
	
		@Autowired
	    private MockMvc mockMvc;
	
	    @MockBean
	    private PortfolioWebService portfolioWebService;	
	
	   @Test
	   public void whenPortfolioExists() {
		   
		   Portfolio portfolio = new Portfolio();
		   portfolio.setIdportfolio(1);
		   portfolio.setDescription("A Portfolio");
		   portfolio.setImage_url("A Image URL");
		   portfolio.setTwitter_user_name("A Twitter User Name");
		   portfolio.setTitle("A Title");
		   
		   
		   try {
			   
			   when(portfolioWebService.getPortfolio(1))
			   .thenReturn(new ResponseEntity<Portfolio>(portfolio, getJSONHeaders(), HttpStatus.OK));
			   
			   mockMvc.perform(get("/application/getportfolio/1"))
				   		.andExpect(status().isOk())
				   		.andExpect(content().json("{'idportfolio':1,'description':'A Portfolio','image_url':'A Image URL','twitter_user_name':'A Twitter User Name','title':'A Title'}"));
		   }
		   catch(Exception ex)
		   {
			   
		   }
		  
	       
	   }
	   
	   @Test
	   public void whenPortfolioDoesNotExist() {
		   
		   try {
			   
			   when(portfolioWebService.getPortfolio(1000))
			   .thenReturn(new ResponseEntity<Portfolio>(null, getJSONHeaders(), HttpStatus.OK));
			   
			   mockMvc.perform(get("/application/getportfolio/1"))
				   		.andExpect(status().isOk())
				   		.andExpect(content().json(""));
		   }
		   catch(Exception ex)
		   {
			   
		   }
		  
	       
	   }
	   
	   @Test
	   public void whenCanGetTweets() {
		   
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTimeInMillis(1577854800000L);
		    
		    FakeTweet fakeTweet =
		    		new FakeTweet(
		    				1L, "1", "text", calendar.getTime(), "user1", "url",
		    				2L, 3L, "language", "source");
		    
		    List<Tweet> tweets = new ArrayList<Tweet>();
		    tweets.add(fakeTweet);
		   
		    try {
		    	
			   when(portfolioWebService.getTweets(1))
			   .thenReturn(new ResponseEntity<List<Tweet>>(tweets, getJSONHeaders(), HttpStatus.OK));
			   
			   mockMvc.perform(get("/application/gettweets/1"))
				   		.andExpect(status().isOk())
				   		.andExpect(content().json("[{'extraData':{},'id':1,'idStr':'1','text':'text','createdAt':'2020-01-01T05:00:00.000+0000','fromUser':'user1','profileImageUrl':'url','toUserId':2,'inReplyToStatusId':null,'inReplyToUserId':null,'inReplyToScreenName':null,'fromUserId':3,'languageCode':'language','source':'source','retweetCount':null,'retweeted':false,'retweetedStatus':null,'favorited':false,'favoriteCount':null,'entities':null,'user':null,'unmodifiedText':'text','retweet':false}]"));
		   }
		   catch(Exception ex)
		   {
			   
		   }
		  
	       
	   }
	   
	   @Test
	   public void whenCantGetTweets() {
		   
		    List<Tweet> tweets = new ArrayList<Tweet>();
		    
		    try {
		    	
			   when(portfolioWebService.getTweets(1))
			   .thenReturn(new ResponseEntity<List<Tweet>>(tweets, getJSONHeaders(), HttpStatus.OK));
			   
			   mockMvc.perform(get("/application/gettweets/1"))
				   		.andExpect(status().isOk())
				   		.andExpect(content().json("[]"));
		   }
		   catch(Exception ex)
		   {
			   
		   }
		  
	       
	   }
	   
	   private HttpHeaders getJSONHeaders()
	    {
	    	final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			return headers;
	    }
	
}
