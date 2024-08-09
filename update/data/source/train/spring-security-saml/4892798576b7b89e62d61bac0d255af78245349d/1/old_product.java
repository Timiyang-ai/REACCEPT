protected boolean processFilter(HttpServletRequest request) {
        return (request.getRequestURI().endsWith(getFilterSuffix()));
    }