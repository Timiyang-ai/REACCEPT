protected static final Map<String, String> parseServiceRequestQueryParams(String queryString) {
        Map<String, String> rval = new LinkedHashMap<>();
        if (queryString != null) {
            try {
                queryString = URLDecoder.decode(queryString, "UTF-8"); //$NON-NLS-1$
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            String[] pairSplit = queryString.split("&"); //$NON-NLS-1$
            for (String paramPair : pairSplit) {
                int idx = paramPair.indexOf("="); //$NON-NLS-1$
                if (idx != -1) {
                    String key = paramPair.substring(0, idx);
                    String val = paramPair.substring(idx + 1);
                    rval.put(key, val);
                } else {
                    rval.put(paramPair, null);
                }
            }
        }

        return rval;
    }