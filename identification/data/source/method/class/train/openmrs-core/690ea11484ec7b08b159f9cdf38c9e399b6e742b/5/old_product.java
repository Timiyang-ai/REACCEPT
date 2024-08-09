public static SimpleDateFormat getDateFormat() {
    	String localeKey = Context.getLocale().toString().toLowerCase();
    	
    	// get the actual pattern from the constants
    	String pattern = OpenmrsConstants.OPENMRS_LOCALE_DATE_PATTERNS().get(localeKey);
    	
    	// default to the "first" locale pattern
    	if (pattern == null)
    		pattern = OpenmrsConstants.OPENMRS_LOCALE_DATE_PATTERNS().get(0);
    	
    	return new SimpleDateFormat(pattern, Context.getLocale());
    }