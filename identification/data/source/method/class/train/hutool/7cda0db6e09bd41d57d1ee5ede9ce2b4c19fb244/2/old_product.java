public static Calendar endOfWeek(Calendar calendar, boolean isSundayAsLastDay) {
		calendar.set(Calendar.DAY_OF_WEEK, isSundayAsLastDay ? Calendar.SUNDAY : Calendar.SATURDAY);
		return endOfDay(calendar);
	}