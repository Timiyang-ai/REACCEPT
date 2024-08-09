public static SimpleDateFormat getDateFormat(Locale locale) {
		if (dateFormatCache.containsKey(locale)) {
			return (SimpleDateFormat) dateFormatCache.get(locale).clone();
		}
		
		// note that we are using the custom OpenmrsDateFormat class here which prevents erroneous parsing of 2-digit years
		SimpleDateFormat sdf = new OpenmrsDateFormat(
		        (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, locale), locale);
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