public static DateTime parseDateTime(Tuple input) throws ExecException {	

	    String isoDateString = input.get(0).toString();
	    DateTime dt = ISODateTimeFormat.dateTimeParser().withOffsetParsed().parseDateTime(isoDateString);			

	    return dt;
	}