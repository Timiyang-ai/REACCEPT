public static Date parseHL7Timestamp(String s) throws HL7Exception {
		
		// HL7 dates must at least contain year and cannot exceed 24 bytes
		if (s == null || s.length() < 4 || s.length() > 24) {
			throw new HL7Exception("Invalid date '" + s + "'");
		}
		
		StringBuffer dateString = new StringBuffer();
		dateString.append(s.substring(0, 4)); // year
		if (s.length() >= 6) {
			dateString.append(s.substring(4, 6)); // month
		} else {
			dateString.append("01");
		}
		if (s.length() >= 8) {
			dateString.append(s.substring(6, 8)); //day
		} else {
			dateString.append("01");
		}
		
		// Parse timezone (optional in HL7 format)
		String timeZoneOffset;
		try {
			Date parsedDay = new SimpleDateFormat("yyyyMMdd").parse(s.substring(0, 8));
			timeZoneOffset = getTimeZoneOffset(s, parsedDay);
		}
		catch (ParseException e) {
			throw new HL7Exception("Error parsing date: '" + s.substring(0, 8) + "' for time zone offset'" + s + "'", e);
		}
		s = s.replace(timeZoneOffset, ""); // remove the timezone from the string
		
		if (s.length() >= 10) {
			dateString.append(s.substring(8, 10)); // hour
		} else {
			dateString.append("00");
		}
		if (s.length() >= 12) {
			dateString.append(s.substring(10, 12)); // minute
		} else {
			dateString.append("00");
		}
		if (s.length() >= 14) {
			dateString.append(s.substring(12, 14)); // seconds
		} else {
			dateString.append("00");
		}
		if (s.length() >= 15 && s.charAt(14) != '.') // decimal point
		{
			throw new HL7Exception("Invalid date format '" + s + "'");
		} else {
			dateString.append(".");
		}
		if (s.length() >= 16) {
			dateString.append(s.substring(15, 16)); // tenths
		} else {
			dateString.append("0");
		}
		if (s.length() >= 17) {
			dateString.append(s.substring(16, 17)); // hundredths
		} else {
			dateString.append("0");
		}
		if (s.length() >= 18) {
			dateString.append(s.subSequence(17, 18)); // milliseconds
		} else {
			dateString.append("0");
		}
		
		dateString.append(timeZoneOffset);
		
		Date date;
		try {
			date = new SimpleDateFormat(TIMESTAMP_FORMAT).parse(dateString.toString());
		}
		catch (ParseException e) {
			throw new HL7Exception("Error parsing date '" + s + "'");
		}
		return date;
	}