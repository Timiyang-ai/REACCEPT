public static String getQueryParam(SlingHttpServletRequest request, String key) {
        return request.getParameter(key);
    }