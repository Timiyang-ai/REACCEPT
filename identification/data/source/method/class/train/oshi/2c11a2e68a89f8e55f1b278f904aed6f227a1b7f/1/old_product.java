public static int parseString(String s, int i) {
        String[] ss = s.split("\\s+");
        if (ss.length < 2) {
            return i;
        } else {
            return Integer.valueOf(ss[ss.length - 1]);
        }
    }