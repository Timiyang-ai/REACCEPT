@Deprecated
        public static void restoreSSLCertificate() {
            throw new IllegalStateException("use HttpClientConfig.setIgnoreSSLCerts(false) instead");
        }