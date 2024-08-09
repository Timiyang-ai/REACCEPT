public static String RTFEnc(String s) {
        int ln = s.length();
        for (int i = 0; i < ln; i++) {
            char c = s.charAt(i);
            if (c == '\\' || c == '{' || c == '}') {
                StringBuilder b =
                        new StringBuilder(s.substring(0, i));
                switch (c) {
                    case '\\': b.append("\\\\"); break;
                    case '{': b.append("\\{"); break;
                    case '}': b.append("\\}"); break;
                }
                i++;
                int next = i;
                while (i < ln) {
                    c = s.charAt(i);
                    if (c == '\\' || c == '{' || c == '}') {
                        b.append(s.substring(next, i));
                        switch (c) {
                            case '\\': b.append("\\\\"); break;
                            case '{': b.append("\\{"); break;
                            case '}': b.append("\\}"); break;
                        }
                        next = i + 1;
                    }
                    i++;
                }
                if (next < ln) b.append(s.substring(next));
                s = b.toString();
                break;
            } // if c ==
        } // for
        return s;
    }