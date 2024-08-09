public IRequestHandler checkSecureIncoming(IRequestHandler handler)
	{

		if (handler instanceof SwitchProtocolRequestHandler)
		{
			return handler;
		}

		Class<?> pageClass = getPageClass(handler);
		if (pageClass != null)
		{
			IRequestHandler redirect = null;
			if (hasSecureAnnotation(pageClass))
			{
				redirect = SwitchProtocolRequestHandler.requireProtocol(Protocol.HTTPS);
			}
			else
			{
				redirect = SwitchProtocolRequestHandler.requireProtocol(Protocol.HTTP);
			}

			if (redirect != null)
			{
				return redirect;
			}

		}
		return handler;
	}