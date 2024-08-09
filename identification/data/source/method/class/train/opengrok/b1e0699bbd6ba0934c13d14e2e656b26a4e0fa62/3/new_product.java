public static void htmlize(char[] cs, int length, StringBuilder dest) {
        int len = length;
        if (cs.length < length) {
            len = cs.length;
        }
        for (int i = 0; i < len; i++ ) {
            htmlize(cs[i], dest);
        }
    }