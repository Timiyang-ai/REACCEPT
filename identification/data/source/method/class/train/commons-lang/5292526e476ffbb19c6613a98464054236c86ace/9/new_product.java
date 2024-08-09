public static CharSet getInstance(final String... setStrs) {
        if (setStrs == null) {
            return null;
        }
        if (setStrs.length == 1) {
            final CharSet common = COMMON.get(setStrs[0]);
            if (common != null) {
                return common;
            }
        }
        return new CharSet(setStrs); 
    }