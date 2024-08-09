@Override
    public String getUserName(HttpServletRequest req) {
        Principal princ;
        if (req != null && (princ = req.getUserPrincipal()) != null) {
            String userName = princ.getName();
            if (userName != null && !userName.isEmpty()) {
                LOG.debug("HTTP request had user ("+userName+")");
                return userName;
            }
        }
        return null;
    }