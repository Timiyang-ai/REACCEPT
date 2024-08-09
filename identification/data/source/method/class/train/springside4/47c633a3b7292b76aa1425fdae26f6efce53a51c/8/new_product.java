public static int getDayOfWeek(@NotNull final Date date) {
		int result = get(date, Calendar.DAY_OF_WEEK);
		return result == 1 ? 7 : result - 1;
	}