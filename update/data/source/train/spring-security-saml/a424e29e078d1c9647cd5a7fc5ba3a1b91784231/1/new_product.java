protected boolean processFilter(HttpServletRequest request) {
        return SAMLUtil.processFilter(FILTER_URL, request);
    }