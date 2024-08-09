public String startRequest() {
        // assure a session exists in the request cache before the request starts
        Session session = makeSession(null);
        // generate a requestId
        String requestId = makeRequestId();
        // call the list of interceptors
        List<RequestInterceptor> interceptors = getInterceptors(false);
        for (RequestInterceptor requestInterceptor : interceptors) {
            if (requestInterceptor != null) {
                try {
                    requestInterceptor.onStart(requestId, session);
                } catch (RequestInterruptionException e) {
                    String message = "Request stopped from starting by exception from the interceptor ("+requestInterceptor+"): " + e.getMessage();
                    log.warn(message);
                    throw new RequestInterruptionException(message, e);
                } catch (Exception e) {
                    log.warn("Request interceptor ("+requestInterceptor+") failed to execute on start ("+requestId+"): " + e.getMessage());
                }
            }
        }
        // put the id into the request cache
        getRequestCache().put(CachingService.REQUEST_ID_KEY, requestId);
        return requestId;
    }