    public final void test_getLocalPrincipal() throws Exception {
        mySSLSession session = new mySSLSession("localhost", 1080, null);
        SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket();
        HandshakeCompletedEvent event = new HandshakeCompletedEvent(socket, session);
        assertNull(event.getLocalPrincipal());
    }