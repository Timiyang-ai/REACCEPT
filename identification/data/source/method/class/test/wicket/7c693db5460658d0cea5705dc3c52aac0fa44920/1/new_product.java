protected final void writeStream(Attributes attributes, InputStream stream) throws IOException
		{
			final Response response = attributes.getResponse();
			Streams.copy(stream, response.getOutputStream());
		}