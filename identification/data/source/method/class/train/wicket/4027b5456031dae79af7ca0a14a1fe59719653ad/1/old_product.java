public static void writeLinkUrl(final Response response, final CharSequence url,
		final CharSequence media, final String markupId, final String rel)
	{
		response.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
		response.write(Strings.escapeMarkup(url));
		response.write("\"");
		if (Strings.isEmpty(media) == false)
		{
			response.write(" media=\"");
			response.write(Strings.escapeMarkup(media));
			response.write("\"");
		}
		if (Strings.isEmpty(markupId) == false)
		{
			response.write(" id=\"");
			response.write(Strings.escapeMarkup(markupId));
			response.write("\"");
		}
		if (Strings.isEmpty(rel) == false)
		{
			response.write(" rel=\"");
			response.write(Strings.escapeMarkup(rel));
			response.write("\"");
		}
		response.write(" />");
	}