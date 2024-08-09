public static long between(Date beginDate, Date endDate, DateUnit unit) {
		return new DateBetween(beginDate, endDate).between(unit);
	}