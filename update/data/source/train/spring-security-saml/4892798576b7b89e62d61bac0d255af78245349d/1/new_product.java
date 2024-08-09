protected boolean processFilter(HttpServletRequest request) {
        return SAMLUtil.processFilter(getFilterProcessesUrl(), request);
    }