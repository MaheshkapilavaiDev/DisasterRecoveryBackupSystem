package com.drbsystem.util;

public class RetryUtil {
	
	public void executeWithRetry(Runnable task, int retries) {

	    for (int i = 0; i < retries; i++) {
	        try {
	            task.run();
	            return;
	        } catch (Exception e) {
	            if (i == retries - 1) throw e;
	        }
	    }
	}

}
