package com.zemoga.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

import com.zemoga.web.dao.PortfolioWebDao;
import com.zemoga.web.data.Portfolio;
import com.zemoga.web.twitter.TwitterConnector;

@Service
public class PortfolioWebService {
	
	@Autowired
	private PortfolioWebDao portfolioWebDao;
	
	@Autowired
	private TwitterConnector twitterConnector;
	   
    public ResponseEntity<Portfolio> getPortfolio(Integer id) {
    	
    	Portfolio portfolio = getPortfolioById(id);
    	
	    return new ResponseEntity<Portfolio>(portfolio, getJSONHeaders(), HttpStatus.OK); 
    }
    
    public ResponseEntity<List<Tweet>> getTweets(Integer id){
    	
    	Portfolio portfolio = getPortfolioById(id);
    	List<Tweet> tweets = new ArrayList<Tweet>();
    	
    	if(null != portfolio)
    	{
    		try {
    			tweets = twitterConnector.getTweetsByUserName(portfolio.getTwitter_user_name());
    		}
    		catch(NotAuthorizedException ex)
    		{
    			return new ResponseEntity<List<Tweet>>(tweets, getJSONHeaders(), HttpStatus.OK);
    		}
    		
    	}
    	
    	return new ResponseEntity<List<Tweet>>(tweets, getJSONHeaders(), HttpStatus.OK);
    }
    
    public ResponseEntity<?> updatePortfolio(Integer id, Portfolio updatedPortfolio)
    {
    	if(getPortfolioById(id) != null)
    	{
    		portfolioWebDao.save(updatedPortfolio);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}	
    	else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    private HttpHeaders getJSONHeaders()
    {
    	final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return headers;
    }
    
    
    private Portfolio getPortfolioById(Integer id)
    {
    	return portfolioWebDao.findById(id).orElse(null);
    }
	

}
