protected Connection obtainInternalConnection() throws SQLException {
		boolean tryAgain = false;
		Connection result = null;
		int acquireRetryAttempts = this.pool.getConfig().getAcquireRetryAttempts();
		int acquireRetryDelay = this.pool.getConfig().getAcquireRetryDelay();
		this.connectionHook = this.pool.getConfig().getConnectionHook();
		do{ 
			try { 
				// keep track of this hook.

				this.connection = DriverManager.getConnection(this.url, this.username, this.password);
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
			} catch (Throwable t) {
				// call the hook, if available.
				if (this.connectionHook != null){
					tryAgain = this.connectionHook.onAcquireFail(t);
				} else {
					logger.error("Failed to acquire connection. Sleeping for "+acquireRetryDelay+"ms. Attempts left: "+acquireRetryAttempts);

					try {
						Thread.sleep(acquireRetryDelay);
						if (acquireRetryAttempts > 0){
							tryAgain = (--acquireRetryAttempts) != 0;
						}
					} catch (InterruptedException e) {
						tryAgain=false;
					}
				}
				if (!tryAgain){
					throw markPossiblyBroken(t);
				}
			}
		} while (tryAgain);

		return result;

	}