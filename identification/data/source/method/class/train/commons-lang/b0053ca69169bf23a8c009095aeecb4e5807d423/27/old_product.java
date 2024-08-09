public static Boolean toBooleanObject(Integer value) {
        if (value == null) {
            return null;
        }
        return (value.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE);
    }