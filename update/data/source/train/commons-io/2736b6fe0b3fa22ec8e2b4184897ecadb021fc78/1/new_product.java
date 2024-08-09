public static int getPrefixLength(final String fileName) {
        if (fileName == null) {
            return NOT_FOUND;
        }
        final int len = fileName.length();
        if (len == 0) {
            return 0;
        }
        char ch0 = fileName.charAt(0);
        if (ch0 == ':') {
            return NOT_FOUND;
        }
        if (len == 1) {
            if (ch0 == '~') {
                return 2;  // return a length greater than the input
            }
            return isSeparator(ch0) ? 1 : 0;
        }
        if (ch0 == '~') {
            int posUnix = fileName.indexOf(UNIX_SEPARATOR, 1);
            int posWin = fileName.indexOf(WINDOWS_SEPARATOR, 1);
            if (posUnix == NOT_FOUND && posWin == NOT_FOUND) {
                return len + 1;  // return a length greater than the input
            }
            posUnix = posUnix == NOT_FOUND ? posWin : posUnix;
            posWin = posWin == NOT_FOUND ? posUnix : posWin;
            return Math.min(posUnix, posWin) + 1;
        }
        final char ch1 = fileName.charAt(1);
        if (ch1 == ':') {
            ch0 = Character.toUpperCase(ch0);
            if (ch0 >= 'A' && ch0 <= 'Z') {
                if (len == 2 || isSeparator(fileName.charAt(2)) == false) {
                    return 2;
                }
                return 3;
            } else if (ch0 == UNIX_SEPARATOR) {
                return 1;
            }
            return NOT_FOUND;

        } else if (isSeparator(ch0) && isSeparator(ch1)) {
            int posUnix = fileName.indexOf(UNIX_SEPARATOR, 2);
            int posWin = fileName.indexOf(WINDOWS_SEPARATOR, 2);
            if (posUnix == NOT_FOUND && posWin == NOT_FOUND || posUnix == 2 || posWin == 2) {
                return NOT_FOUND;
            }
            posUnix = posUnix == NOT_FOUND ? posWin : posUnix;
            posWin = posWin == NOT_FOUND ? posUnix : posWin;
            int pos = Math.min(posUnix, posWin) + 1;
            String hostnamePart = fileName.substring(2, pos - 1);
            return isValidHostName(hostnamePart) ? pos : NOT_FOUND;
        } else {
            return isSeparator(ch0) ? 1 : 0;
        }
    }