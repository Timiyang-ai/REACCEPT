public static int parseIntDef(String s, int def) {
        try {
            int i = Integer.parseInt(s);
            return i;
        } catch (NumberFormatException e) {
            return def;
        }
    }