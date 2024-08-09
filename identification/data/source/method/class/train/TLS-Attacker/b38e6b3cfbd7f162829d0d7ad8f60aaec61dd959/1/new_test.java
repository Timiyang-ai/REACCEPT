@Test
    public void testGetCipherType() {
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_NULL_WITH_NULL_NULL) == CipherType.STREAM);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_IDEA_CBC_SHA) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_RC4_128_SHA) == CipherType.STREAM);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_AES_128_CCM) == CipherType.AEAD);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_AES_256_CCM) == CipherType.AEAD);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256) == CipherType.AEAD);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384) == CipherType.AEAD);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256) == CipherType.AEAD);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384) == CipherType.AEAD);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_DES_CBC_SHA) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.SSL_FORTEZZA_KEA_WITH_FORTEZZA_CBC_SHA) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_ARIA_128_CBC_SHA256) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_ARIA_128_GCM_SHA256) == CipherType.AEAD);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_DH_anon_WITH_ARIA_256_CBC_SHA384) == CipherType.BLOCK);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_RSA_WITH_ARIA_256_GCM_SHA384) == CipherType.AEAD);
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_GOSTR341094_WITH_28147_CNT_IMIT) == CipherType.BLOCK); //?
        assertTrue(AlgorithmResolver.getCipherType(CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256) == CipherType.STREAM);
    }