@Sensitive
    public static String getCookieValue(Cookie[] cookies, String cookieName) {
        if (cookies == null) {
            return null;
        }

        String retVal = null;
        for (int i = 0; i < cookies.length; ++i) {
            if (cookieName.equalsIgnoreCase(cookies[i].getName())) {
                retVal = cookies[i].getValue();
                break;
            }
        }

        return retVal;
    }