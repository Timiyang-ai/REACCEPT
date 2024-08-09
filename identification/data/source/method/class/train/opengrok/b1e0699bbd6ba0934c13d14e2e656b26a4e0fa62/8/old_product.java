private static final void htmlize(char c, StringBuilder dest) {
        switch (c) {
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
                dest.append("<br/>");
                break;
            default:
                dest.append(c);
        }
    }