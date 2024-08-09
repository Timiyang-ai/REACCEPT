public static String RTFEnc(String s) {
        int ln = s.length();
        
        // First we find out if we need to escape, and if so, what the length of the output will be:
        int firstEscIdx = -1;
        int lastEscIdx = 0;
        int plusOutLn = 0;
        for (int i = 0; i < ln; i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '}' || c == '\\') {
                if (firstEscIdx == -1) {
                    firstEscIdx = i;
                }
                lastEscIdx = i;
                plusOutLn++;
            }
        }
        
        if (firstEscIdx == -1) {
            return s; // Nothing to escape
        } else {
            char[] esced = new char[ln + plusOutLn];
            if (firstEscIdx != 0) {
                s.getChars(0, firstEscIdx, esced, 0);
            }
            int dst = firstEscIdx;
            for (int i = firstEscIdx; i <= lastEscIdx; i++) {
                char c = s.charAt(i);
                if (c == '{' || c == '}' || c == '\\') {
                    esced[dst++] = '\\';
                }
                esced[dst++] = c;
            }
            if (lastEscIdx != ln - 1) {
                s.getChars(lastEscIdx + 1, ln, esced, dst);
            }
            
            return String.valueOf(esced);
        }
    }