public static DateTime parse(String dateString, String format) {
		return parse(dateString, new SimpleDateFormat(format));
	}