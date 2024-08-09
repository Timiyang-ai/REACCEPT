public static Date parseHL7Time(String s) throws HL7Exception {
		
		// Parse timezone (optional in HL7 format)
		String timeZoneOffset;
		int tzPlus = s.indexOf('+');
		int tzMinus = s.indexOf('-');
		boolean timeZoneFlag = (tzPlus > 0 || tzMinus > 0);
		if (timeZoneFlag) {
			int tzIndex;
			if (tzPlus > 0)
				tzIndex = tzPlus;
			else
				tzIndex = tzMinus;
			timeZoneOffset = s.substring(tzIndex);
			if (timeZoneOffset.length() != 5)
				System.out.println("invalid timestamp");
			s = s.substring(0, tzIndex);
		} else
			timeZoneOffset = LOCAL_TIMEZONE_OFFSET;
		
		StringBuffer timeString = new StringBuffer();
		
		if (s.length() < 2 || s.length() > 16)
			throw new HL7Exception("Invalid time format '" + s + "'");
		
		timeString.append(s.substring(0, 2)); // hour
		if (s.length() >= 4)
			timeString.append(s.substring(2, 4)); // minute
		else
			timeString.append("00");
		if (s.length() >= 6)
			timeString.append(s.substring(4, 6)); // seconds
		else
			timeString.append("00");
		if (s.length() >= 7 && s.charAt(6) != '.') // decimal point
			throw new HL7Exception("Invalid time format '" + s + "'");
		else
			timeString.append(".");
		if (s.length() >= 8)
			timeString.append(s.substring(7, 8)); // tenths
		else
			timeString.append("0");
		if (s.length() >= 9)
			timeString.append(s.substring(8, 9)); // hundredths
		else
			timeString.append("0");
		if (s.length() >= 10)
			timeString.append(s.subSequence(9, 10)); // milliseconds
		else
			timeString.append("0");
		timeString.append(timeZoneOffset);
		
		Date date;
		try {
			date = (Date) TIME_FORMAT.parse(timeString.toString());
		}
		catch (ParseException e) {
			throw new HL7Exception("Invalid time format: '" + s + "' [" + timeString + "]", e);
		}
		return date;
	}