public static boolean toBoolean(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return (bool.booleanValue() ? true : false);
    }