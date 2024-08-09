public static int weekOfYear(Date date) {
		return calendar(date).get(Calendar.WEEK_OF_YEAR);
	}