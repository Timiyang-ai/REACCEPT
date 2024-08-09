protected static final QueryMap parseApiRequestQueryParams(String queryString) {
        QueryMap rval = new QueryMap();
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
                    rval.add(key, val);
                } else {
                    rval.add(paramPair, null);
                }
            }
        }

        return rval;
    }