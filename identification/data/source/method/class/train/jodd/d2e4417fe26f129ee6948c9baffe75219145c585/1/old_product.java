public static void startTomcat() {
		if (server != null) {
			return;
		}
		server = new TomcatTestServer();
		try {
			server.start();
			System.out.println("Tomcat test server started");
		} catch (Exception e) {
			throw new UncheckedException(e);
		}
	}