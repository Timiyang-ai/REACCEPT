public static String FTLStringLiteralEnc(String s)
    {
        StringBuffer buf = null;
        int l = s.length();
        int el = ESCAPES.length;
        for(int i = 0; i < l; i++)
        {
            char c = s.charAt(i);
            if(c < el)
            {
                char escape = ESCAPES[c];
                switch(escape)
                {
                    case 0:
                    {
                        if (buf != null) {
                            buf.append(c);
                        }
                        break;
                    }
                    case 1:
                    {
                        if (buf == null) {
                            buf = new StringBuffer(s.length() + 3);
                            buf.append(s.substring(0, i));
                        }
                        // hex encoding for characters below 0x20
                        // that have no other escape representation
                        buf.append("\\x00");
                        int c2 = (c >> 4) & 0x0F;
                        c = (char) (c & 0x0F);
                        buf.append((char) (c2 < 10 ? c2 + '0' : c2 - 10 + 'A'));
                        buf.append((char) (c < 10 ? c + '0' : c - 10 + 'A'));
                        break;
                    }
                    default:
                    {
                        if (buf == null) {
                            buf = new StringBuffer(s.length() + 2);
                            buf.append(s.substring(0, i));
                        }
                        buf.append('\\');
                        buf.append(escape);
                    }
                }
            } else {
                if (buf != null) {
                    buf.append(c);
                }
            }
        }
        return buf == null ? s : buf.toString();
    }