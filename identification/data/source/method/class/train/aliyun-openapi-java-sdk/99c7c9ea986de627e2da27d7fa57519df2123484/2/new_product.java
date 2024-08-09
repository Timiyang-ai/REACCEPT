@Deprecated
        public static void restoreSSLCertificate() {
            X509TrustAll.ignoreSSLCerts = false;
        }