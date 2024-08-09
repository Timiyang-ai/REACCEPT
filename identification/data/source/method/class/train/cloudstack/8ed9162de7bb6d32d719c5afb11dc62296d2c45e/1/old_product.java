public static String encodeURIComponent(String url) {
        int schemeTail = url.indexOf("://");

        int pathStart = 0;
        if (schemeTail > 0)
            pathStart = url.indexOf('/', schemeTail + 3);
        else
            pathStart = url.indexOf('/');

        if (pathStart > 0) {
            String[] tokens = url.substring(pathStart + 1).split("/");
            if (tokens != null) {
                StringBuffer sb = new StringBuffer();
                sb.append(url.substring(0, pathStart));
                for (String token : tokens) {
                    sb.append("/").append(URLEncoder.encode(token));
                }

                return sb.toString();
            }
        }

        // no need to do URL component encoding
        return url;
    }