public Integer getAge(Date onDate) {
		if (birthdate == null)
			return null;
		
		Calendar today = Calendar.getInstance();
		if (onDate != null)
			today.setTime(onDate);
		
		Calendar bday = new GregorianCalendar();
		bday.setTime(birthdate);
		
		int age = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
		
		//Adjust age when today's date is before the person's birthday
		int todaysMonth = today.get(Calendar.MONTH);
		int bdayMonth = bday.get(Calendar.MONTH);
		int todaysDay = today.get(Calendar.DAY_OF_MONTH);
		int bdayDay = bday.get(Calendar.DAY_OF_MONTH);
		
		if (todaysMonth < bdayMonth) {
			age--;
		} else if (todaysMonth == bdayMonth && todaysDay < bdayDay) {
			// we're only comparing on month and day, not minutes, etc
			age--;
		}
		
		return age;
	}