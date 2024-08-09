public static SimpleDateFormat getDateFormat(Locale locale) {
		if (dateFormatCache.containsKey(locale))
			return (SimpleDateFormat) dateFormatCache.get(locale).clone();
		
		SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, locale);
		String pattern = sdf.toPattern();
		
		if (!pattern.contains("yyyy")) {
			// otherwise, change the pattern to be a four digit year
			pattern = pattern.replaceFirst("yy", "yyyy");
			sdf.applyPattern(pattern);
		}
		if (!pattern.contains("MM")) {
			// change the pattern to be a two digit month
			pattern = pattern.replaceFirst("M", "MM");
			sdf.applyPattern(pattern);
		}
		if (!pattern.contains("dd")) {
			// change the pattern to be a two digit day
			pattern = pattern.replaceFirst("d", "dd");
			sdf.applyPattern(pattern);
		}
		
		dateFormatCache.put(locale, sdf);
		
		return (SimpleDateFormat) sdf.clone();
	}