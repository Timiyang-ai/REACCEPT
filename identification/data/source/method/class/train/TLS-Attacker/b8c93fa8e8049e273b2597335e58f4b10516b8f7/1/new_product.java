public static PRFAlgorithm getPRFAlgorithm(ProtocolVersion protocolVersion, CipherSuite cipherSuite) {
        PRFAlgorithm result;
        if (protocolVersion == ProtocolVersion.SSL3 || protocolVersion == ProtocolVersion.SSL2) {
            throw new UnsupportedOperationException("SSL3 and SSL2 PRF currently not supported");
        }
        if (protocolVersion == ProtocolVersion.TLS10 || protocolVersion == ProtocolVersion.TLS11
                || protocolVersion == ProtocolVersion.DTLS10) {
            result = PRFAlgorithm.TLS_PRF_LEGACY;
        } else if (cipherSuite.usesSHA384()) {
            result = PRFAlgorithm.TLS_PRF_SHA384;
        } else if (cipherSuite.usesGOSTR3411()) {
            result = PRFAlgorithm.TLS_PRF_GOSTR3411;
        } else {
            result = PRFAlgorithm.TLS_PRF_SHA256;
        }
        LOGGER.debug("Using the following PRF Algorithm: {}", result);
        return result;
    }