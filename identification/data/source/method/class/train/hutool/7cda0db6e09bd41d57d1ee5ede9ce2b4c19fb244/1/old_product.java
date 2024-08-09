public static Calendar endOfWeek(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		return endOfDay(calendar);
	}