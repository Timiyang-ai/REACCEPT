@Deprecated
        public static void ignoreSSLCertificate() {
            throw new IllegalStateException("use HttpClientConfig.setIgnoreSSLCerts(true) instead");
        }