@Trivial
    public static String getRequestStringForTrace(HttpServletRequest request, String[] secretStrings) {
        if (request == null) {
            return "[]";
        }
        String url = ((request.getRequestURL()==null)?null:request.getRequestURL().toString());
        String queryString = request.getQueryString();

        return "["+stripSecretsFromUrl(url,secretStrings)+"], queryString["+((queryString==null)?"":stripSecretsFromUrl(queryString,secretStrings))+"]";
    }