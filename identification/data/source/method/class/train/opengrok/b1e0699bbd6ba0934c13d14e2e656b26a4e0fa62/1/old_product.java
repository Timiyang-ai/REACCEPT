public static void htmlize(CharSequence q, StringBuilder dest) {
        for (int i = 0; i < q.length(); i++ ) {
            htmlize(q.charAt(i), dest);
        }
    }