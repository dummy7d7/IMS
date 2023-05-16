package com.ims.security;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class BlockListCacheService {

	private LoadingCache<String, String> loginAttemptCache;

	public BlockListCacheService() {
		super();
		loginAttemptCache = CacheBuilder.newBuilder().expireAfterWrite(15, TimeUnit.MINUTES).maximumSize(100)
				.build(new CacheLoader<String, String>() {
					public String load(String key) {
						return "";
					}
				});

	}

	public void evictUserFromBlockList(String username) {
		loginAttemptCache.invalidate(username);
	}

	public void addUserToBlockList(String username, String token) {

		loginAttemptCache.put(username, token);

	}

	public boolean isUserBlocked(String username) {
		String checkVal = "";
		try {
			checkVal = loginAttemptCache.get(username);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
//		System.out.println("UN"+checkVal);
		return "".equals(checkVal);
	}
}