@Override
	public Connection connect(String url, Properties info) throws SQLException {
		if ("false".equals(info.get("javamelody"))) {
			// if property javamelody=false then it's not for us
			// (we pass here from the DriverManager.getConnection below)
			return null;
		}
		final Properties myInfo = (Properties) info.clone();
		myInfo.put("javamelody", "false");
		String myUrl = url;
		// we load first the driver class from the info or the url, to be sure that it will be found
		String proxiedDriver = myInfo.getProperty("driver");
		if (proxiedDriver != null) {
			myInfo.remove("driver");
		} else if (myUrl != null) {
			// if not in the info, the driver class could also be passed at the end of the url, for example ...?driver=org.h2.Driver
			final int index = myUrl.indexOf("driver=");
			if (index != -1) {
				proxiedDriver = myUrl.substring(index + "driver=".length());
				myUrl = myUrl.substring(0, index - 1);
			}
		}
		if (proxiedDriver != null) {
			try {
				// on utilise Thread.currentThread().getContextClassLoader() car le driver peut ne pas être
				// dans le même classLoader que les classes de javamelody
				// Class driverClass =
				Class.forName(proxiedDriver, true, Thread.currentThread().getContextClassLoader());
				// et non Class.forName(proxiedDriver);
			} catch (final ClassNotFoundException e) {
				throw new SQLException(e.getMessage(), e);
			}
		}
		// but if the driver is not defined in the info or in the url
		// it will still be found automatically if the driver is in the classpath
		// or (in WEB-INF/lib and if the jdbc drivers are not loaded by the JDK before this webapp)

		Parameters.initJdbcDriverParameters(myUrl, myInfo);
		// we could call driverClass.newInstance().connect(myUrl, myInfo)
		// possibly by looking the driver which accepts the url in DriverManager.getDrivers()
		// but we prefer calling the standard DriverManager.getConnection(myUrl, myInfo)
		return JdbcWrapper.SINGLETON
				.createConnectionProxy(DriverManager.getConnection(myUrl, myInfo));
	}