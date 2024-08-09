public static String getQueryParam(final SlingHttpServletRequest request, final String key) {
        return request.getParameter(key);
    }