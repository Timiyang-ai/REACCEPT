public static DateTime parseDateTime(Tuple input) throws ExecException {	
	        
	    // Save previous default time zone for restore later.
	    DateTimeZone previousDefaultTimeZone = DateTimeZone.getDefault();

	    // Temporarily set default time zone to UTC, for this parse.
	    DateTimeZone.setDefault(DEFAULT_DATE_TIME_ZONE);

	    String isoDateString = input.get(0).toString();
	    DateTime dt = ISODateTimeFormat.dateTimeParser().withOffsetParsed().parseDateTime(isoDateString);			

	    // restore previous default TimeZone.
	    DateTimeZone.setDefault(previousDefaultTimeZone);

	    return dt;
	}