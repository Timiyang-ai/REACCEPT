protected final void writeStream(Attributes attributes, InputStream stream)
		{
			final Response response = attributes.getResponse();
			try
			{
				Streams.copy(stream, response.getOutputStream());
			}
			catch (IOException e)
			{
				throw new WicketRuntimeException(e);
			}
		}