Key getSigningKey(JwtConsumerConfig config, JwtContext jwtContext, Map properties) throws KeyException {
        Key signingKey = null;
        if (config == null) {
            if (tc.isDebugEnabled()) {
                Tr.debug(tc, "JWT consumer config object is null");
            }
            return null;
        }
        signingKey = getSigningKeyBasedOnSignatureAlgorithm(config, jwtContext, properties);
        if (signingKey == null) {
            if (tc.isDebugEnabled()) {
                Tr.debug(tc, "A signing key could not be found");
            }
        }
        return signingKey;
    }