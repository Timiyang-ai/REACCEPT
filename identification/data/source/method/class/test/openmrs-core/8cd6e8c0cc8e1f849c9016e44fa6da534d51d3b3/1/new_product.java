public static String format(Date date, Locale locale, FORMAT_TYPE type) {
		if (date == null || locale == null || type == null) {
			return "";
		}
		log.debug("Formatting date: " + date + " with locale " + locale);
		
		DateFormat dateFormat;
		
		if (type == FORMAT_TYPE.TIMESTAMP) {
			dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		} else if (type == FORMAT_TYPE.TIME) {
			dateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM, locale);
		} else {
			dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		}
		return dateFormat.format(date);
	}