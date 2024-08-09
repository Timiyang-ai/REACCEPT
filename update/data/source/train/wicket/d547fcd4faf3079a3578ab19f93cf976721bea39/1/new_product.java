public static PathLocale getLocaleFromFilename(String path)
	{
		String extension = "";

		final int pos = path.lastIndexOf('.');
		if (pos != -1)
		{
			extension = path.substring(pos);
			path = path.substring(0, pos);
		}

		String filename = Strings.lastPathComponent(path, '/');
		Matcher matcher = LOCALE_MIN_PATTERN.matcher(filename);
		if (matcher.find())
		{
			String language = matcher.group(1);
			String country = matcher.group(3);
			String variant = matcher.group(5);
			String min = matcher.group(6);

			// did we find a language?
			if (language != null)
			{
				if (isoLanguages.contains(language) == false)
				{
					language = null;
					country = null;
					variant = null;
				}
			}

			// did we find a country?
			if ((language != null) && (country != null))
			{
				if (isoCountries.contains(country) == false)
				{
					country = null;
					variant = null;
				}
			}

			if (language != null)
			{
				int languagePos = path.length() - filename.length() + matcher.start();
				String basePath = path.substring(0, languagePos) + (min == null ? "" : min) +
					extension;

				Locale locale = new Locale(language, country != null ? country : "",
					variant != null ? variant : "");

				return new PathLocale(basePath, locale);
			}
		} // else skip the whole thing... probably user specific underscores used

		return new PathLocale(path + extension, null);
	}