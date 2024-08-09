public static Boolean toBooleanObject(String str) {
        if ("true".equalsIgnoreCase(str)) {
            return Boolean.TRUE;
        } else if ("false".equalsIgnoreCase(str)) {
            return Boolean.FALSE;
        } else if ("on".equalsIgnoreCase(str)) {
            return Boolean.TRUE;
        } else if ("off".equalsIgnoreCase(str)) {
            return Boolean.FALSE;
        } else if ("yes".equalsIgnoreCase(str)) {
            return Boolean.TRUE;
        } else if ("no".equalsIgnoreCase(str)) {
            return Boolean.FALSE;
        }
        // no match
        return null;
    }