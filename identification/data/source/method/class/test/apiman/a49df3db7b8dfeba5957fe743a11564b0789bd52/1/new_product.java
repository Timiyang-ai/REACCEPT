protected static final ServiceRequestPathInfo parseServiceRequestPath(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        ServiceRequestPathInfo info = new ServiceRequestPathInfo();

        boolean versionFound = false;

        String apiVersionHeader = request.getHeader("X-API-Version"); //$NON-NLS-1$
        if (apiVersionHeader != null && apiVersionHeader.trim().length() > 0) {
            info.serviceVersion = apiVersionHeader;
            versionFound = true;
        } else {
            String acceptHeader = request.getHeader("Accept"); //$NON-NLS-1$
            if (acceptHeader != null && acceptHeader.startsWith("application/apiman.")) { //$NON-NLS-1$
                String [] split = acceptHeader.split("\\+"); //$NON-NLS-1$
                info.serviceVersion = split[0].substring("application/apiman.".length()); //$NON-NLS-1$
                versionFound = true;
            }
        }

        int minParts = versionFound ? 3 : 4;

        if (pathInfo != null) {
            String[] split = pathInfo.split("/"); //$NON-NLS-1$
            if (split.length >= minParts) {
                info.orgId = split[1];
                info.serviceId = split[2];
                if (!versionFound) {
                    info.serviceVersion = split[3];
                }
                if (split.length > minParts) {
                    StringBuilder resource = new StringBuilder();
                    for (int idx = minParts; idx < split.length; idx++) {
                        resource.append('/');
                        resource.append(split[idx]);
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