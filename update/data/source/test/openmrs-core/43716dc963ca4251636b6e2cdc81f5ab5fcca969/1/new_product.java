public void setDate(Calendar calendar) {
		setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), false,
		    false, false);
	}