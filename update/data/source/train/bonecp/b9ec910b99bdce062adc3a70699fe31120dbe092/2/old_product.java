public synchronized void shutdown(){

		if (!this.poolShuttingDown){
			logger.info("Shutting down connection pool...");
			this.poolShuttingDown = true;
			this.shutdownStackTrace = captureStackTrace(SHUTDOWN_LOCATION_TRACE);
			this.keepAliveScheduler.shutdownNow(); // stop threads from firing.
			this.maxAliveScheduler.shutdownNow(); // stop threads from firing.
			this.connectionsScheduler.shutdownNow(); // stop threads from firing.
			this.asyncExecutor.shutdownNow();

			try {
				this.connectionsScheduler.awaitTermination(5, TimeUnit.SECONDS);

				this.maxAliveScheduler.awaitTermination(5, TimeUnit.SECONDS);
				this.keepAliveScheduler.awaitTermination(5, TimeUnit.SECONDS);
				this.asyncExecutor.awaitTermination(5, TimeUnit.SECONDS);
				
				if (this.closeConnectionExecutor != null){
					this.closeConnectionExecutor.shutdownNow();
					this.closeConnectionExecutor.awaitTermination(5, TimeUnit.SECONDS);
				}
				if(!this.config.isDisableJMX()) {
					unregisterJMX();
				}

			} catch (InterruptedException e) {
				// do nothing
			}
			this.connectionStrategy.terminateAllConnections();
			unregisterDriver();
			registerUnregisterJMX(false);
			logger.info("Connection pool has been shutdown.");
		}
	}