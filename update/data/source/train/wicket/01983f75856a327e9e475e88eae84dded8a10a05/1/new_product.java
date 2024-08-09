public static String stripJSessionId(final String url)
	{
		if (Strings.isEmpty(url))
		{
			return url;
		}

		// http://.../abc;jsessionid=...?param=...
		int ixSemiColon = url.toLowerCase(Locale.ENGLISH).indexOf(";jsessionid=");
		if (ixSemiColon == -1)
		{
			return url;
		}

		int ixQuestionMark = url.indexOf('?');
		if (ixQuestionMark == -1)
		{
			// no query paramaters; cut off at ";"
			// http://.../abc;jsession=...
			return url.substring(0, ixSemiColon);
		}

		if (ixQuestionMark <= ixSemiColon)
		{
			// ? is before ; - no jsessionid in the url
			return url;
		}

		return url.substring(0, ixSemiColon) + url.substring(ixQuestionMark);
	}