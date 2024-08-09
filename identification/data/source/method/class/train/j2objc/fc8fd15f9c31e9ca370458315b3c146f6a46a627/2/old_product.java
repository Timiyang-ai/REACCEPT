public Principal getPeerPrincipal()
            throws SSLPeerUnverifiedException
    {
        Principal principal;
        try {
            principal = session.getPeerPrincipal();
        } catch (AbstractMethodError e) {
            // if the provider does not support it, fallback to peer certs.
            // return the X500Principal of the end-entity cert.
            Certificate[] certs = getPeerCertificates();
            principal = (X500Principal)
                ((X509Certificate)certs[0]).getSubjectX500Principal();
        }
        return principal;
    }