public static DateTime parse(CharSequence dateStr, String format, Locale locale) {
		return new DateTime(dateStr, new SimpleDateFormat(format, locale));
	}