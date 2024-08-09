public static void htmlize(char[] cs, int length, StringBuilder dest) {
        if (cs.length < length) {
            length = cs.length;
        }
        for (int i = 0; i < length; i++ ) {
            htmlize(cs[i], dest);
        }
    }