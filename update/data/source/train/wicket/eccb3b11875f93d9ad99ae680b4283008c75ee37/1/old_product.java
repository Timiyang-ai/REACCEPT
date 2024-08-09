public static void writeJavaScriptUrl(final Response response, final CharSequence url,
		final String id, boolean defer, String charset)
	{
		response.write("<script type=\"text/javascript\" ");
		if (id != null)
		{
			response.write("id=\"" + Strings.escapeMarkup(id) + "\" ");
		}
		if (defer)
		{
			response.write("defer=\"defer\" ");
		}
		if (charset != null)
		{
			response.write("charset=\"" + Strings.escapeMarkup(charset) + "\" ");
		}
		response.write("src=\"");
		response.write(Strings.escapeMarkup(url));
		response.write("\"></script>");
		response.write("\n");
	}