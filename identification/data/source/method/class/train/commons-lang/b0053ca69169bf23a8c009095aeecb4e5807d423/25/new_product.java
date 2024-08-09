public static boolean isFalse(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue() ? false : true;
    }