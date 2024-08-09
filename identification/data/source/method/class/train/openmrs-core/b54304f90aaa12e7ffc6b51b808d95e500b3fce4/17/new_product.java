protected static String getTimeZoneOffset(String fullString, Date givenDate) {
		// Parse timezone (optional in HL7 format)
		String timeZoneOffset;
		int tzPlus = fullString.indexOf('+');
		int tzMinus = fullString.indexOf('-');
		boolean timeZoneFlag = (tzPlus > 0 || tzMinus > 0);
		if (timeZoneFlag) {
			int tzIndex;
			if (tzPlus > 0) {
				tzIndex = tzPlus;
			} else {
				tzIndex = tzMinus;
			}
			timeZoneOffset = fullString.substring(tzIndex);
			if (timeZoneOffset.length() != 5) {
				log.error("Invalid timestamp because its too short: " + timeZoneOffset);
			}
			
		} else {
			//set default timezone offset from the current day
			Calendar cal = Calendar.getInstance();
			cal.setTime(givenDate);
			timeZoneOffset = new SimpleDateFormat("Z").format(cal.getTime());
		}
		
		return timeZoneOffset;
	}