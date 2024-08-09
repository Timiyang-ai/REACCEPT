public static String escapeAttribute(String value) {
        StringBuilder escaped = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {
            char chr = value.charAt(i);

            if (chr != '\'' && chr != '"' && chr != '<' && chr != '>' && chr != '/') {
                escaped.append(chr);
            }
        }

        return escaped.toString();
    }