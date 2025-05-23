public static String flatten(String s[], String separator) {
        if(s==null)
            return null;

        StringBuilder sb = new StringBuilder();
        int sLen = s.length;
        boolean first = true;
        String el;

        for(int i=0; i<sLen; i++) {
            el = s[i];
            if (isNullOrEmpty(el))
                continue;

            if(first)
                first = false;
            else
                sb.append(separator);

            sb.append(el);
        }

        return sb.toString();
    }