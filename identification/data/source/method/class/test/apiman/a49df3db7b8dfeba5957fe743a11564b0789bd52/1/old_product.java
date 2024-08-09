protected static final ServiceRequestPathInfo parseServiceRequestPath(String pathInfo) {
        ServiceRequestPathInfo info = new ServiceRequestPathInfo();
        if (pathInfo != null) {
            String[] split = pathInfo.split("/"); //$NON-NLS-1$
            if (split.length >= 4) {
                info.orgId = split[1];
                info.serviceId = split[2];
                info.serviceVersion = split[3];
                if (split.length > 4) {
                    StringBuilder resource = new StringBuilder();
                    for (int idx = 4; idx < split.length; idx++) {
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