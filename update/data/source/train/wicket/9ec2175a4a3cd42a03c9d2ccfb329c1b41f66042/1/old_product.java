public void respond(IRequestCycle requestCycle)
	{
		WebRequest webRequest = (WebRequest)requestCycle.getRequest();
		HttpServletRequest request = ((ServletWebRequest)webRequest).getHttpServletRequest();

		final HttpsConfig httpsConfig = Application.get().getSecuritySettings().getHttpsConfig();

		Integer port = null;
		if (protocol == Protocol.HTTP)
		{
			if (httpsConfig.getHttpPort() != 80)
			{
				port = httpsConfig.getHttpPort();
			}
		}
		else if (protocol == Protocol.HTTPS)
		{
			if (httpsConfig.getHttpsPort() != 443)
			{
				port = httpsConfig.getHttpsPort();
			}
		}

		final String url;
		if (handler == null)
		{
			url = getUrl(protocol.toString().toLowerCase(), port, request);
		}
		else
		{
			url = ((RequestCycle)requestCycle).urlFor(handler).toString();
		}

		WebResponse response = (WebResponse)requestCycle.getResponse();

		// an attempt to rewrite a secure jsessionid into nonsecure, doesnt seem to work
		// Session session = Session.get();
		// if (!session.isTemporary())
		// {
		// response.addCookie(new Cookie("JSESSIONID", session.getId()));
		// }

		response.sendRedirect(url);
	}