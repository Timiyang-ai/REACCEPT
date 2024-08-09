public static void writeLinkUrl(final Response response, final CharSequence url, final CharSequence media)
	{
		response.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
		response.write(Strings.escapeMarkup(url));
		response.write("\"");
		if (media != null)
		{
			response.write(" media=\"");
			response.write(Strings.escapeMarkup(media));
			response.write("\"");
		}
		response.write(" />");
	}