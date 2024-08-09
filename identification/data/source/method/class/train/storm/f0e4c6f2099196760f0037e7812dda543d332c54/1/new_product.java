@Override
    public String getUserName(HttpServletRequest req) {
        String ret = null;
        if (req != null) {
            Principal princ = req.getUserPrincipal();
            if (princ != null) {
                ret = princ.getName();
            }

            if (ret != null && !ret.isEmpty()) {
                LOG.debug("Get user name {} from http request principal", ret);
            } else {
                ret = req.getRemoteUser();
                if (ret != null && !ret.isEmpty()) {
                    LOG.debug("Get user name {} from http request remote user", ret);
                }
            }
        }
        return ret;
    }