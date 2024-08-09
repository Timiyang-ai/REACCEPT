public Integer getAge(Date onDate) {
		if (birthdate == null)
			return null;
		
		Calendar today = Calendar.getInstance();
		if (onDate != null)
			today.setTime(onDate);
		
		Calendar bday = new GregorianCalendar();
		bday.setTime(birthdate);
		
		int age = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
		
		//tricky bit:
		// set birthday calendar to this year
		// if the current date is less that the new 'birthday', subtract a year
		bday.set(Calendar.YEAR, today.get(Calendar.YEAR));
		if (today.before(bday))
			age = age - 1;
		
		return age;
	}