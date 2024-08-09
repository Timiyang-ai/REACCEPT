@Test
	public void testContextInitialized() {
		ServletContext servletContext = createNiceMock(ServletContext.class);
		expect(servletContext.getServerInfo()).andReturn("Mock");
		ServletContextEvent servletContextEvent = new ServletContextEvent(servletContext);
		replay(servletContext);
		sessionListener.contextInitialized(servletContextEvent);
		sessionListener.contextDestroyed(servletContextEvent);
		verify(servletContext);

		Utils.setProperty(Parameter.NO_DATABASE, "true");
		servletContext = createNiceMock(ServletContext.class);
		expect(servletContext.getServerInfo()).andReturn("Mock");
		servletContextEvent = new ServletContextEvent(servletContext);
		replay(servletContext);
		sessionListener.contextInitialized(servletContextEvent);
		sessionListener.contextDestroyed(servletContextEvent);
		verify(servletContext);
	}