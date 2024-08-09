public void configure(Properties props)
	throws HibernateException {
		String connectionTestStatement = props.getProperty(CONFIG_TEST_STATEMENT);

		int preparedStatementCacheSize = configParseNumber(props, CONFIG_PREPARED_STATEMENT_CACHE_SIZE, 50);
		int statementsCachedPerConnection = configParseNumber(props, CONFIG_STATEMENTS_CACHED_PER_CONNECTION, 30);
		int minsize = configParseNumber(props, CONFIG_MIN_CONNECTIONS_PER_PARTITION, 20);
		int maxsize = configParseNumber(props, CONFIG_MAX_CONNECTIONS_PER_PARTITION, 50);
		int acquireIncrement = configParseNumber(props, CONFIG_ACQUIRE_INCREMENT, 10);
		int partcount = configParseNumber(props, CONFIG_PARTITION_COUNT, 3);
		int releaseHelperThreads = configParseNumber(props, CONFIG_RELEASE_HELPER_THREADS, 3);
		long idleMaxAge = configParseNumber(props, CONFIG_IDLE_MAX_AGE, 240);
		long idleConnectionTestPeriod = configParseNumber(props, CONFIG_IDLE_CONNECTION_TEST_PERIOD, 60);

		String url = props.getProperty(CONFIG_CONNECTION_URL, "JDBC URL NOT SET IN CONFIG");
		String username = props.getProperty(CONFIG_CONNECTION_USERNAME, "username not set");
		String password = props.getProperty(CONFIG_CONNECTION_PASSWORD, "password not set");

		// Remember Isolation level
		this.isolation = PropertiesHelper.getInteger(Environment.ISOLATION, props);
		this.autocommit = PropertiesHelper.getBoolean(Environment.AUTOCOMMIT, props);

		try {
			Class.forName(props.getProperty(CONFIG_CONNECTION_DRIVER_CLASS));
			logger.debug(String.format(CONFIG_STATUS, url, username, minsize, maxsize, acquireIncrement, partcount, idleConnectionTestPeriod/1000, idleMaxAge/(60*1000)));
			this.config = new BoneCPConfig();
			this.config.setMinConnectionsPerPartition(minsize);
			this.config.setMaxConnectionsPerPartition(maxsize);
			this.config.setAcquireIncrement(acquireIncrement);
			this.config.setPartitionCount(partcount);
			this.config.setJdbcUrl(url);
			this.config.setUsername(username);
			this.config.setPassword(password);
			this.config.setReleaseHelperThreads(releaseHelperThreads);
			this.config.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
			this.config.setIdleMaxAge(idleMaxAge);
			this.config.setConnectionTestStatement(connectionTestStatement);
			this.config.setPreparedStatementsCacheSize(preparedStatementCacheSize);
			this.config.setStatementsCachedPerConnection(statementsCachedPerConnection);
			this.config.sanitize();

			// create the connection pool
			this.pool = createPool(this.config);
		}
		catch (NullPointerException e) {
			throw new HibernateException(e);
		}

		catch (ClassNotFoundException e) {
			throw new HibernateException(e);
		}

	}