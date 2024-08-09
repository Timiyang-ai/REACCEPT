public static final String getUntaggedName(String appName)
            throws VersioningSyntaxException {

        if(appName != null && !appName.isEmpty()){
            int colonIndex = appName.indexOf(EXPRESSION_SEPARATOR);
            // if versioned
            if (colonIndex != -1) {

                // if appName is ending with a colon
                if (colonIndex == (appName.length() - 1)) {
                    throw new VersioningSyntaxException(
                            LOCALSTRINGS.getLocalString("invalid.appname",
                            "excepted version identifier after colon: {0}",
                            appName));
                }
                return appName.substring(0, colonIndex);
            }
        }
        // not versioned
        return appName;
    }