protected Connection obtainInternalConnection(ConnectionHandle connectionHandle) throws SQLException {
		boolean tryAgain = false;
		Connection result = null;
		String url = this.getConfig().getJdbcUrl();
		
		int acquireRetryAttempts = this.getConfig().getAcquireRetryAttempts();
		long acquireRetryDelayInMs = this.getConfig().getAcquireRetryDelayInMs();
		AcquireFailConfig acquireConfig = new AcquireFailConfig();
		acquireConfig.setAcquireRetryAttempts(new AtomicInteger(acquireRetryAttempts));
		acquireConfig.setAcquireRetryDelayInMs(acquireRetryDelayInMs);
		acquireConfig.setLogMessage("Failed to acquire connection to "+url);
		ConnectionHook connectionHook = this.getConfig().getConnectionHook();
		do{ 
			result = null;
			try { 
				// keep track of this hook.
				result = this.obtainRawInternalConnection();
				tryAgain = false;

				if (acquireRetryAttempts != this.getConfig().getAcquireRetryAttempts()){
					logger.info("Successfully re-established connection to "+url);
				}
				
				this.getDbIsDown().set(false);
				
				// call the hook, if available.
				if (connectionHook != null){
					connectionHook.onAcquire(connectionHandle);
				}

				
				ConnectionHandle.sendInitSQL(result, this.getConfig().getInitSQL());
			} catch (SQLException e) {
				// call the hook, if available.
				if (connectionHook != null){
					tryAgain = connectionHook.onAcquireFail(e, acquireConfig);
				} else {
					logger.error(String.format("Failed to acquire connection to %s. Sleeping for %d ms. Attempts left: %d", url, acquireRetryDelayInMs, acquireRetryAttempts), e);

					try {
						if (acquireRetryAttempts > 0){
							Thread.sleep(acquireRetryDelayInMs);
	 					}
						tryAgain = (acquireRetryAttempts--) > 0;
					} catch (InterruptedException e1) {
						tryAgain=false;
					}
				}
				if (!tryAgain){
					throw e;
				}
			}
		} while (tryAgain);

		return result;

	}