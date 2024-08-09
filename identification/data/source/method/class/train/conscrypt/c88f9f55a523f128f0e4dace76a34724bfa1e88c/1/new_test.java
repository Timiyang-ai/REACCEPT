    @Override
    NativeSslSession getCachedSession(ClientSessionContext context, NativeSslSession s) {
        return context.getCachedSession(s.getPeerHost(), DEFAULT_PORT,
                getDefaultSSLParameters());
    }