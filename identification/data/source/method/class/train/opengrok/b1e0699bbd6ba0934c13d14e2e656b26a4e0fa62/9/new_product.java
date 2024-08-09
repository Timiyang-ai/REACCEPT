private static void htmlize(char c, Appendable dest) throws IOException {
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