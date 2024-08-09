public static boolean toBoolean(String str) {
        if ("true".equalsIgnoreCase(str)) {
            return true;
        } else if ("on".equalsIgnoreCase(str)) {
            return true;
        } else if ("yes".equalsIgnoreCase(str)) {
            return true;
        }
        // no match
        return false;
    }