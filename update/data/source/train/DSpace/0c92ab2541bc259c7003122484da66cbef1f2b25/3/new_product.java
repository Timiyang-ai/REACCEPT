public String endRequest(Exception failure) {
        String requestId = null;
        try {
            requestId = getCurrentRequestId();
            if (StringUtils.isEmpty(requestId)) {
                // request not found, just log a warning
                log.debug("Attempting to end a request when none currently exists");
            } else {
                endRequest(requestId, failure);
            }
        } finally {
            requests.removeCurrent();
        }
        return requestId;
    }