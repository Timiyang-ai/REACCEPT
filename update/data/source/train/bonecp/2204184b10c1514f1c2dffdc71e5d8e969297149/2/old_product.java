protected Connection obtainInternalConnection() throws SQLException {
		boolean tryAgain = false;
		Connection result = null;

		int acquireRetryAttempts = this.pool.getConfig().getAcquireRetryAttempts();
		int acquireRetryDelay = this.pool.getConfig().getAcquireRetryDelay();
		AcquireFailConfig acquireConfig = new AcquireFailConfig();
		acquireConfig.setAcquireRetryAttempts(new AtomicInteger(acquireRetryAttempts));
		acquireConfig.setAcquireRetryDelay(acquireRetryDelay);
		acquireConfig.setLogMessage("Failed to acquire connection");

		this.connectionHook = this.pool.getConfig().getConnectionHook();
		do{ 
			try { 
				// keep track of this hook.
				this.connection = this.pool.obtainRawInternalConnection();
				tryAgain = false;

				if (acquireRetryAttempts != this.pool.getConfig().getAcquireRetryAttempts()){
					logger.info("Successfully re-established connection to DB");
				}
				// call the hook, if available.
				if (this.connectionHook != null){
					this.connectionHook.onAcquire(this);
				}

				sendInitSQL();
				result = this.connection;
			} catch (SQLException e) {
				// call the hook, if available.
				if (this.connectionHook != null){
					tryAgain = this.connectionHook.onAcquireFail(e, acquireConfig);
				} else {
					logger.error("Failed to acquire connection. Sleeping for "+acquireRetryDelay+"ms. Attempts left: "+acquireRetryAttempts, e);

					try {
						Thread.sleep(acquireRetryDelay);
						if (acquireRetryAttempts > 0){
							tryAgain = (--acquireRetryAttempts) != 0;
						}
					} catch (InterruptedException e1) {
						tryAgain=false;
					}
				}
				if (!tryAgain){
					throw markPossiblyBroken(e);
				}
			}
		} while (tryAgain);

		return result;

	}