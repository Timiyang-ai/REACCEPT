@Test
    public void testGetDigestAlgorithm() {
        for (CipherSuite suite : CipherSuite.values()) {
            assertTrue(AlgorithmResolver.getDigestAlgorithm(ProtocolVersion.TLS10, suite) == DigestAlgorithm.LEGACY);
            assertTrue(AlgorithmResolver.getDigestAlgorithm(ProtocolVersion.TLS11, suite) == DigestAlgorithm.LEGACY);
            assertTrue(AlgorithmResolver.getDigestAlgorithm(ProtocolVersion.DTLS10, suite) == DigestAlgorithm.LEGACY);
        }
        assertTrue(AlgorithmResolver.getDigestAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384) == DigestAlgorithm.SHA384);
        assertTrue(AlgorithmResolver.getDigestAlgorithm(ProtocolVersion.DTLS12,
                CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384) == DigestAlgorithm.SHA384);
        assertTrue(AlgorithmResolver.getDigestAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.TLS_CECPQ1_ECDSA_WITH_CHACHA20_POLY1305_SHA256) == DigestAlgorithm.SHA256);
        assertTrue(AlgorithmResolver.getDigestAlgorithm(ProtocolVersion.DTLS12,
                CipherSuite.TLS_CECPQ1_ECDSA_WITH_CHACHA20_POLY1305_SHA256) == DigestAlgorithm.SHA256);
        assertTrue(AlgorithmResolver.getDigestAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.SSL_FORTEZZA_KEA_WITH_FORTEZZA_CBC_SHA) == DigestAlgorithm.SHA256);
        assertTrue(AlgorithmResolver.getDigestAlgorithm(ProtocolVersion.DTLS12,
                CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM) == DigestAlgorithm.SHA256);
        assertTrue(AlgorithmResolver.getDigestAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5) == DigestAlgorithm.SHA256);
        assertTrue(AlgorithmResolver.getDigestAlgorithm(ProtocolVersion.TLS12,
                CipherSuite.TLS_GOSTR341094_WITH_28147_CNT_IMIT) == DigestAlgorithm.GOSTR3411);
    }