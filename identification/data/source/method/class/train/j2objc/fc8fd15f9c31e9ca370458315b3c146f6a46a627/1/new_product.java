public Principal getLocalPrincipal()
    {
        Principal principal;
        try {
            principal = session.getLocalPrincipal();
        } catch (AbstractMethodError e) {
            principal = null;
            // if the provider does not support it, fallback to local certs.
            // return the X500Principal of the end-entity cert.
            Certificate[] certs = getLocalCertificates();
            if (certs != null) {
                principal =
                        ((X509Certificate)certs[0]).getSubjectX500Principal();
            }
        }
        return principal;
    }