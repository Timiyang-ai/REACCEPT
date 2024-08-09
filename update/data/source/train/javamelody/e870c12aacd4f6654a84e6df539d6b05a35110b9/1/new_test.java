@Test
	public void testContextInitialized() {
		ServletContext servletContext = createNiceMock(ServletContext.class);
		expect(servletContext.getServerInfo()).andReturn("Mock").anyTimes();
		expect(servletContext.getMajorVersion()).andReturn(2).anyTimes();
		expect(servletContext.getMinorVersion()).andReturn(5).anyTimes();
		ServletContextEvent servletContextEvent = new ServletContextEvent(servletContext);
		replay(servletContext);
		sessionListener.contextInitialized(servletContextEvent);
		sessionListener.contextDestroyed(servletContextEvent);
		verify(servletContext);

		Utils.setProperty(Parameter.NO_DATABASE, "true");
		servletContext = createNiceMock(ServletContext.class);
		expect(servletContext.getServerInfo()).andReturn("Mock").anyTimes();
		expect(servletContext.getMajorVersion()).andReturn(2).anyTimes();
		expect(servletContext.getMinorVersion()).andReturn(5).anyTimes();
		servletContextEvent = new ServletContextEvent(servletContext);
		replay(servletContext);
		sessionListener.contextInitialized(servletContextEvent);
		sessionListener.contextDestroyed(servletContextEvent);
		verify(servletContext);
	}