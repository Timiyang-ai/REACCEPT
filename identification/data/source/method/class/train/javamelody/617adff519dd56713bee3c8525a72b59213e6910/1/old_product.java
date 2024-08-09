@Override
	public Connection connect(String url, Properties info) throws SQLException {
		final String proxiedDriver = info.getProperty("driver");
		if (proxiedDriver == null) {
			// si pas de propriété driver alors ce n'est pas pour nous
			// (on passe ici lors du DriverManager.getConnection ci-dessous)
			return null;
		}
		try {
			// on utilise Thread.currentThread().getContextClassLoader() car le driver peut ne pas être
			// dans le même classLoader que les classes de javamelody
			Class.forName(proxiedDriver, true, Thread.currentThread().getContextClassLoader());
			// et non Class.forName(proxiedDriver);
		} catch (final ClassNotFoundException e) {
			// Rq: le constructeur de SQLException avec message et cause n'existe qu'en jdk 1.6
			throw new SQLException(e.getMessage(), e);
		}
		final Properties tmp = (Properties) info.clone();
		tmp.remove("driver");
		Parameters.initJdbcDriverParameters(url, tmp);
		return JdbcWrapper.SINGLETON.createConnectionProxy(DriverManager.getConnection(url, tmp));
	}