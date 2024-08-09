public Date getDate() {
		//TODO
		Integer year;
		if (isFlag(UNKNOWN_YEAR) || getYear() == null) {
			year = 0;
		} else {
			year = getYear();
		}
		
		Integer month;
		if (isFlag(UNKNOWN_MONTH) || getMonth() == null) {
			month = 7;
		} else {
			month = getMonth();
		}
		
		Integer date;
		if (isFlag(UNKNOWN_DAY) || getDay() == null) {
			date = 15;
		} else {
			date = getDay();
		}
		
		if (isFlag(UNKNOWN_MONTH) && isFlag(UNKNOWN_DAY) || getMonth() == null && getDay() == null) {
			month = 7;
			date = 1;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0L);
		cal.set(year, month - 1, date, 0, 0, 0);
		return cal.getTime();
	}