public static int parseLastElementOfStringToInt(String s, int i) {
        String[] ss = s.split("\\s+");
        if (ss.length < 2) {
            return i;
        } else {
            try {
                return Integer.parseInt(ss[ss.length - 1]);
            } catch (NumberFormatException nfe) {
                return i;
            }
        }
    }