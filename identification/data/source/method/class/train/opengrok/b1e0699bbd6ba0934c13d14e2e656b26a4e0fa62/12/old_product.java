public static String htmlize(CharSequence q) {
        StringBuilder sb = new StringBuilder(q.length() * 2);
        htmlize(q, sb);
        return sb.toString();
    }