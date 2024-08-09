public static String XMLEncNQG(String s) {
        int ln = s.length();
        for (int i = 0; i < ln; i++) {
            char c = s.charAt(i);
            if (c == '<'
                    || (c == '>' && i > 1
                            && s.charAt(i - 1) == ']'
                            && s.charAt(i - 2) == ']')
                    || c == '&') {
                StringBuilder b =
                        new StringBuilder(s.substring(0, i));
                switch (c) {
                    case '<': b.append("&lt;"); break;
                    case '>': b.append("&gt;"); break;
                    case '&': b.append("&amp;"); break;
                    default: throw new RuntimeException("Bug: unexpected char");
                }
                i++;
                int next = i;
                while (i < ln) {
                    c = s.charAt(i);
                    if (c == '<'
                            || (c == '>' && i > 1
                                    && s.charAt(i - 1) == ']'
                                    && s.charAt(i - 2) == ']')
                            || c == '&') {
                        b.append(s.substring(next, i));
                        switch (c) {
                            case '<': b.append("&lt;"); break;
                            case '>': b.append("&gt;"); break;
                            case '&': b.append("&amp;"); break;
                            default: throw new RuntimeException("Bug: unexpected char");
                        }
                        next = i + 1;
                    }
                    i++;
                }
                if (next < ln) {
                    b.append(s.substring(next));
                }
                s = b.toString();
                break;
            } // if c ==
        } // for
        return s;
    }