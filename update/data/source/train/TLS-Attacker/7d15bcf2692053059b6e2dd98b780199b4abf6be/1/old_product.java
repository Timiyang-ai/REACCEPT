public static DigestAlgorithm getDigestAlgorithm(ProtocolVersion protocolVersion, CipherSuite cipherSuite) {
        DigestAlgorithm result;
        if (protocolVersion == ProtocolVersion.SSL3 || protocolVersion == ProtocolVersion.SSL2) {
            throw new UnsupportedOperationException("SSL3 and SSL2 PRF currently not supported");
        }
        if (cipherSuite.usesGOSTR3411()) {
            result = DigestAlgorithm.GOSTR3411;
        } else if (cipherSuite.usesGOSTR34112012()) {
            result = DigestAlgorithm.GOSTR3411_2012_256;
        } else if (protocolVersion == ProtocolVersion.TLS10 || protocolVersion == ProtocolVersion.TLS11
                || protocolVersion == ProtocolVersion.DTLS10) {
            result = DigestAlgorithm.LEGACY;
        } else if (cipherSuite.usesSHA384()) {
            result = DigestAlgorithm.SHA384;
        } else {
            result = DigestAlgorithm.SHA256;
        }
        LOGGER.debug("Using the following Digest Algorithm: {}", result);
        return result;
    }