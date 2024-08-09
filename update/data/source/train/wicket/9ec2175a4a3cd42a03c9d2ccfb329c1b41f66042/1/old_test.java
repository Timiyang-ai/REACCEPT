@Test
	public void respond() throws MalformedURLException
	{
		// the URL to redirect to
		final URL httpsUrl = new URL("https://example.com:1443/app?param1=value1&param2=value2");

		final HttpServletRequest httpRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(httpRequest.getServerName()).thenReturn(httpsUrl.getHost());
		Mockito.when(httpRequest.getRequestURI()).thenReturn(httpsUrl.getPath());
		Mockito.when(httpRequest.getQueryString()).thenReturn(httpsUrl.getQuery());

		final Url url = Url.parse(httpsUrl.getPath() + "?" + httpsUrl.getQuery());
		final ServletWebRequest webRequest = new ServletWebRequest(httpRequest, "", url);

		final IRequestCycle requestCycle = Mockito.mock(IRequestCycle.class);
		Mockito.when(requestCycle.getRequest()).thenReturn(webRequest);

		// request secure communication (over https)
		final SwitchProtocolRequestHandler handler = new SwitchProtocolRequestHandler(
			Protocol.HTTPS);

		final WebResponse webResponse = Mockito.mock(WebResponse.class);
		Mockito.when(requestCycle.getResponse()).thenReturn(webResponse);

		final WebApplication application = new MockApplication();

		// needed to be able to call Application.get().getSecuritySettings().getHttpsConfig()
		final WicketTester tester = new WicketTester(application);
		ThreadContext.setApplication(application);
		application.getSecuritySettings().setHttpsConfig(new HttpsConfig(80, 1443));

		handler.respond(requestCycle);

		Mockito.verify(webResponse).sendRedirect(httpsUrl.toString());
	}