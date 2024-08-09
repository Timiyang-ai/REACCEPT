public String endRequest(Exception failure) {
        String requestId = getCurrentRequestId();
        if (requestId != null) {
            try {
                getRequestCache().remove(CachingService.REQUEST_ID_KEY);
                // get session and execute the interceptors
                Session session = getCurrentSession();
                List<RequestInterceptor> interceptors = getInterceptors(true); // reverse
                for (RequestInterceptor requestInterceptor : interceptors) {
                    if (requestInterceptor != null) {
                        try {
                            requestInterceptor.onEnd(requestId, session, (failure == null), failure);
                        } catch (RequestInterruptionException e) {
                            log.warn("Attempt to stop request from ending by an exception from the interceptor ("+requestInterceptor+"), cannot stop requests from ending though so request end continues, this may be an error: " + e.getMessage());
                        } catch (Exception e) {
                            log.warn("Request interceptor ("+requestInterceptor+") failed to execute on end ("+requestId+"): " + e.getMessage());
                        }
                    }
                }
            } finally {
                // purge the request caches
                cachingService.unbindRequestCaches();
            }
        } else {
            // request not found, just log a warning
            log.debug("Attempting to end a request when none currently exists");
        }
        return requestId;
    }