public static Integer toIntegerObject(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return (bool.booleanValue() ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO);
    }