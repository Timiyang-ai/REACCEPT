public static String escapeAttribute(String value) {
        StringBuilder escaped = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {
            char chr = value.charAt(i);

            if (chr == '<') {
                escaped.append("&lt;");
            } else if (chr == '>') {
                escaped.append("&gt;");
            } else if (chr == '"') {
                escaped.append("&quot;");
            } else if (chr == '\'') {
                escaped.append("&apos;");
            } else if (chr == '&') {
                escaped.append("&amp;");
            } else {
                escaped.append(chr);
            }
        }

        return escaped.toString();
    }