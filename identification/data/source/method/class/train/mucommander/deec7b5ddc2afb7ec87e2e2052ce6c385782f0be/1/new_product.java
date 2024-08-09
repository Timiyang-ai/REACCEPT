public static String capitalize(String s) {
        if(isNullOrEmpty(s))
            return EMPTY;

        StringBuilder out;

        out = new StringBuilder(s.length());
        out.append(Character.toUpperCase(s.charAt(0)));
        if(s.length() > 1)
            out.append(s.substring(1).toLowerCase());
        return out.toString();
    }