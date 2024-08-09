	protected static void startTomcat(final String webXmlFileName) {
		if (server != null) {
			return;
		}
		server = new TomcatTestServer(webXmlFileName);
		try {
			server.start();
			System.out.println("Tomcat test server started");
		} catch (final Exception e) {
			throw new UncheckedException(e);
		}
	}