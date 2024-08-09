@Override
    public String toString() {
        StringBuilder buf = new StringBuilder(24);
        buf.append("PT");
        if (seconds < 0 && nanos > 0) {
            if (seconds == -1) {
                buf.append("-0");
            } else {
                buf.append(seconds + 1);
            }
        } else {
            buf.append(seconds);
        }
        if (nanos > 0) {
            int pos = buf.length();
            if (seconds < 0) {
                buf.append(2 * NANOS_PER_SECOND - nanos);
            } else {
                buf.append(nanos + NANOS_PER_SECOND);
            }
            while (buf.charAt(buf.length() - 1) == '0') {
                buf.setLength(buf.length() - 1);
            }
            buf.setCharAt(pos, '.');
        }
        buf.append('S');
        return buf.toString();
    }