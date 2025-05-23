@Test
	public void checkSecureIncoming()
	{
		final WebApplication application = new MockApplication();

		// needed to be able to call new WebPage()
		final WicketTester tester = new WicketTester(application);
		ThreadContext.setApplication(application);

		HttpsRequestChecker checker = new HttpsRequestChecker();

		IPageRequestHandler httpsPageRequestHandler = Mockito.mock(IPageRequestHandler.class);
		Mockito.when(httpsPageRequestHandler.getPage()).thenReturn(new HttpsPage());

		HttpsConfig httpsConfig = new HttpsConfig();

		IRequestHandler httpsPageSecureIncoming = checker.checkSecureIncoming(
			httpsPageRequestHandler, httpsConfig);
		assertTrue(httpsPageSecureIncoming instanceof SwitchProtocolRequestHandler);

		IPageRequestHandler httpPageRequestHandler = Mockito.mock(IPageRequestHandler.class);
		Mockito.when(httpPageRequestHandler.getPage()).thenReturn(new HttpPage());

		IRequestHandler httpPageSecureIncoming = checker.checkSecureIncoming(
			httpPageRequestHandler, httpsConfig);
		assertSame(httpPageRequestHandler, httpPageSecureIncoming);
	}