@Trivial
    public static String getRequestStringForTrace(HttpServletRequest request, String[] secretStrings) {
        if (request==null || request.getRequestURL()==null) {
            return "[]";
        }

        StringBuffer sb = new StringBuffer("["+stripSecretsFromUrl(request.getRequestURL().toString(),secretStrings)+"]");

        String query = request.getQueryString();
        if (query!=null) {
            String queryString = stripSecretsFromUrl(query,secretStrings);
            if (queryString!=null) {
                sb.append(", queryString["+queryString+"]");
            }
        } else {
            Map<String, String[]> pMap = request.getParameterMap();
            String paramString = stripSecretsFromParameters(pMap, secretStrings);
            if (paramString!=null) {
                sb.append(", parameters["+paramString+"]");
            }
        }
        return sb.toString();
    }