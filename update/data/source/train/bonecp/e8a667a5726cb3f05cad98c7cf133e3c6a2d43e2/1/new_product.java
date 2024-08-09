public boolean onAcquireFail(Throwable t, AcquireFailConfig acquireConfig) {
		boolean tryAgain = false;
		String log = acquireConfig.getLogMessage();
		logger.error(log+" Sleeping for "+acquireConfig.getAcquireRetryDelay()+"ms and trying again. Attempts left: "+acquireConfig.getAcquireRetryAttempts()+". Exception: "+t.getCause());

		try {
			Thread.sleep(acquireConfig.getAcquireRetryDelay());
			if (acquireConfig.getAcquireRetryAttempts().get() > 0){
				tryAgain = (acquireConfig.getAcquireRetryAttempts().decrementAndGet()) > 0;
			}
		} catch (Exception e) {
			tryAgain=false;
		}
 
		return tryAgain;  
	}