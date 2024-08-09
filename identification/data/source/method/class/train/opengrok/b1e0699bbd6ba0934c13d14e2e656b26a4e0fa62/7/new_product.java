public static void htmlize(char[] cs, int length, Appendable dest)
            throws IOException {
        int len = length;
        if (cs.length < length) {
            len = cs.length;
        }
        for (int i = 0; i < len; i++) {
            htmlize(cs[i], dest, false);
        }
    }