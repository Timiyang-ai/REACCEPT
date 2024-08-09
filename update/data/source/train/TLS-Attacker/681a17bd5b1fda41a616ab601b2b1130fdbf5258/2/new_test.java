@Test
    public void testGetPRFAlgorithm() {
        // Some protocol versions should always return tls_legacy
        for (CipherSuite suite : CipherSuite.values()) {
            assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.TLS10, suite) == PRFAlgorithm.TLS_PRF_LEGACY);
            assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.TLS11, suite) == PRFAlgorithm.TLS_PRF_LEGACY);
            assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.DTLS10, suite) == PRFAlgorithm.TLS_PRF_LEGACY);
        }
        assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384) == PRFAlgorithm.TLS_PRF_SHA384);
        assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.DTLS12,
                CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384) == PRFAlgorithm.TLS_PRF_SHA384);
        assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.TLS_CECPQ1_ECDSA_WITH_CHACHA20_POLY1305_SHA256) == PRFAlgorithm.TLS_PRF_SHA256);
        assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.DTLS12,
                CipherSuite.TLS_CECPQ1_ECDSA_WITH_CHACHA20_POLY1305_SHA256) == PRFAlgorithm.TLS_PRF_SHA256);
        assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.SSL_FORTEZZA_KEA_WITH_FORTEZZA_CBC_SHA) == PRFAlgorithm.TLS_PRF_SHA256);
        assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.DTLS12, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM) == PRFAlgorithm.TLS_PRF_SHA256);
        assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5) == PRFAlgorithm.TLS_PRF_SHA256);
        assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.TLS_GOSTR341001_WITH_28147_CNT_IMIT) == PRFAlgorithm.TLS_PRF_GOSTR3411);
        assertTrue(AlgorithmResolver.getPRFAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.TLS_GOSTR341112_256_WITH_28147_CNT_IMIT) == PRFAlgorithm.TLS_PRF_GOSTR3411_2012_256);
    }