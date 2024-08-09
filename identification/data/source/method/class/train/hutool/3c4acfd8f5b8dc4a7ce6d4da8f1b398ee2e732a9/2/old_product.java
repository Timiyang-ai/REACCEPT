public static DateTime offsetDate(Date date, DateField datePart, int offset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(datePart.getValue(), offset);
		return new DateTime(cal.getTime());
	}