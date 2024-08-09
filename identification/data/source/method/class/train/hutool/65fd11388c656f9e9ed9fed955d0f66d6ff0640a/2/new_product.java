public static DateTime date(Date date) {
		if (date instanceof DateTime) {
			return (DateTime) date;
		}
		return new DateTime(date);
	}