static protected String getStateFromStatusString(final String status, final Capabilities version) {
        String retValue = null;
        String REGEX;
        
        if (version == Capabilities.STATUS_C) {
            REGEX = "(?<=\\<)[a-zA-z]*(?=[,])";
        } else {
            return null;
        }
        
        
        // Search for a version.
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(status);
        if (matcher.find()) {
            retValue = matcher.group(0);;
        }

        return retValue;
    }