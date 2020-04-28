package com.zemoga.web.controller.test;

import java.util.Date;

import org.springframework.social.twitter.api.Tweet;

public class FakeTweet extends Tweet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FakeTweet(long id, String idStr, String text, Date createdAt, String fromUser, String profileImageUrl,
			Long toUserId, long fromUserId, String languageCode, String source) {

		super(id, idStr, text, createdAt, fromUser, profileImageUrl, toUserId, fromUserId, languageCode, source);
		// TODO Auto-generated constructor stub
	}

}
