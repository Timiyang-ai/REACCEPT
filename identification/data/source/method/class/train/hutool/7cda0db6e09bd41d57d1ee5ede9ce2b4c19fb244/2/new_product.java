public static Calendar endOfWeek(Calendar calendar, boolean isSundayAsLastDay) {
		if(isSundayAsLastDay) {
			//设置周一为一周开始
			calendar.setFirstDayOfWeek(Week.MONDAY.getValue());
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		}else {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		}
		return endOfDay(calendar);
	}