public void contextInitialized(ServletContextEvent event) {
		final String contextPath = event.getServletContext().getContextPath();
		if (!instanceEnabled) {
			if (!CONTEXT_PATHS.contains(contextPath)) {
				// si jars dans tomcat/lib, il y a plusieurs instances mais dans des webapps différentes (issue 193)
				instanceEnabled = true;
			} else {
				return;
			}
		}
		CONTEXT_PATHS.add(contextPath);
		// lecture de la propriété système java.io.tmpdir uniquement
		// pour lancer une java.security.AccessControlException si le SecurityManager est activé,
		// avant d'avoir une ExceptionInInitializerError pour la classe Parameters
		System.getProperty("java.io.tmpdir");

		Parameters.initialize(event.getServletContext());

		LOG.debug("JavaMelody listener init started");

		// on initialise le monitoring des DataSource jdbc même si cette initialisation
		// sera refaite dans MonitoringFilter au cas où ce listener ait été oublié dans web.xml
		final JdbcWrapper jdbcWrapper = JdbcWrapper.SINGLETON;
		jdbcWrapper.initServletContext(event.getServletContext());
		if (!Parameters.isNoDatabase()) {
			jdbcWrapper.rebindDataSources();
		}

		LOG.debug("JavaMelody listener init done");
	}