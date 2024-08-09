public static Date parseHL7Time(String s) throws HL7Exception {
		
		String timeZoneOffset = getTimeZoneOffset(s, new Date());
		s = s.replace(timeZoneOffset, ""); // remove the timezone from the string
		
		StringBuilder timeString = new StringBuilder();
		
		if (s.length() < 2 || s.length() > 16) {
			throw new HL7Exception("Invalid time format '" + s + "'");
		}
		
		timeString.append(s.substring(0, 2)); // hour
		if (s.length() >= 4) {
			timeString.append(s.substring(2, 4)); // minute
		} else {
			timeString.append("00");
		}
		if (s.length() >= 6) {
			timeString.append(s.substring(4, 6)); // seconds
		} else {
			timeString.append("00");
		}
		if (s.length() >= 7 && s.charAt(6) != '.') {
			// decimal point
			throw new HL7Exception("Invalid time format '" + s + "'");
		} else {
			timeString.append(".");
		}
		if (s.length() >= 8) {
			timeString.append(s.substring(7, 8)); // tenths
		} else {
			timeString.append("0");
		}
		if (s.length() >= 9) {
			timeString.append(s.substring(8, 9)); // hundredths
		} else {
			timeString.append("0");
		}
		if (s.length() >= 10) {
			timeString.append(s.subSequence(9, 10)); // milliseconds
		} else {
			timeString.append("0");
		}
		
		// Parse timezone (optional in HL7 format)
		timeString.append(timeZoneOffset);
		
		Date date;
		try {
			date = new SimpleDateFormat(TIME_FORMAT).parse(timeString.toString());
		}
		catch (ParseException e) {
			throw new HL7Exception("Invalid time format: '" + s + "' [" + timeString + "]", e);
		}
		return date;
	}