private static void htmlize(char c, Appendable dest, boolean pre)
            throws IOException {
        switch (c) {
            case '\'':
                dest.append("&apos;");
                break;
            case '"':
                dest.append("&quot;");
                break;
            case '&':
                dest.append("&amp;");
                break;
            case '>':
                dest.append("&gt;");
                break;
            case '<':
                dest.append("&lt;");
                break;
            case '\n':
                if (pre) {
                    dest.append(c);                
                } else {
                    dest.append("<br/>");
                }
                break;
            default:
                if ((c >= ' ' && c <= '~') || (c < ' ' &&
                    Character.isWhitespace(c))) {
                    dest.append(c);
                } else {
                    dest.append("&#").append(Integer.toString(c)).append(';');
                }
                break;
        }
    }