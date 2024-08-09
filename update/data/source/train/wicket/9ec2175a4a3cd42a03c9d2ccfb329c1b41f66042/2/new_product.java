IRequestHandler checkSecureIncoming(IRequestHandler requestHandler,
		final HttpsConfig httpsConfig)
	{

		if (requestHandler instanceof SwitchProtocolRequestHandler)
		{
			return requestHandler;
		}

		Class<?> pageClass = getPageClass(requestHandler);
		if (pageClass != null)
		{
			IRequestHandler redirect = null;
			if (hasSecureAnnotation(pageClass))
			{
				redirect = SwitchProtocolRequestHandler.requireProtocol(Protocol.HTTPS, httpsConfig);
			}
			else
			{
				redirect = SwitchProtocolRequestHandler.requireProtocol(Protocol.HTTP, httpsConfig);
			}

			if (redirect != null)
			{
				return redirect;
			}

		}
		return requestHandler;
	}