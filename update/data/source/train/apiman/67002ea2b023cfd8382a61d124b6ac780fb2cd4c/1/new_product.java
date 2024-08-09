public static final ApiRequestPathInfo parseApiRequestPath(String apiVersionHeader, String acceptHeader, String pathInfo) {
        //String pathInfo = request.getPathInfo();
        ApiRequestPathInfo info = new ApiRequestPathInfo();

        boolean versionFound = false;

        //String apiVersionHeader = request.getHeader("X-API-Version"); //$NON-NLS-1$
        if (apiVersionHeader != null && apiVersionHeader.trim().length() > 0) {
            info.apiVersion = apiVersionHeader;
            versionFound = true;
        } else {
            //String acceptHeader = request.getHeader("Accept"); //$NON-NLS-1$
            if (acceptHeader != null && acceptHeader.startsWith("application/apiman.")) { //$NON-NLS-1$
                String [] split = acceptHeader.split("\\+"); //$NON-NLS-1$
                info.apiVersion = split[0].substring("application/apiman.".length()); //$NON-NLS-1$
                versionFound = true;
            }
        }

        int minParts = versionFound ? 3 : 4;

        if (pathInfo != null) {
            String[] split = pathInfo.split("/"); //$NON-NLS-1$
            if (split.length >= minParts) {
                info.orgId = split[1];
                info.apiId = split[2];
                if (!versionFound) {
                    info.apiVersion = split[3];
                }
                if (split.length > minParts) {
                    StringBuilder resource = new StringBuilder();
                    for (int idx = minParts; idx < split.length; idx++) {
                        resource.append('/');
                        resource.append(urlEncode(split[idx]));
                    }
                    if (pathInfo.endsWith("/")) { //$NON-NLS-1$
                        resource.append('/');
                    }
                    info.resource = resource.toString();
                }
            }
        }
        return info;
    }