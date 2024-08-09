public static void htmlize(CharSequence q, Appendable dest)
            throws IOException {
        for (int i = 0; i < q.length(); i++) {
            htmlize(q.charAt(i), dest);
        }
    }