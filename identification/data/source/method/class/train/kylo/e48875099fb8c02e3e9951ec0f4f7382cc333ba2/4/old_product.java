@Nonnull
    @Override
    protected String encodeCookie(@Nonnull final String[] tokens) {
        // Determine expiration time
        final NumericDate expireTime = NumericDate.fromMilliseconds(DateTimeUtils.currentTimeMillis());
        expireTime.addSeconds(getExpirationTimeSeconds());

        // Build the JSON Web Token
        final JwtClaims claims = new JwtClaims();
        claims.setExpirationTime(expireTime);
        claims.setSubject(tokens[0]);
        claims.setStringListClaim("groups", Arrays.asList(tokens).subList(1, tokens.length));

        // Generate a signature
        final JsonWebSignature jws = new JsonWebSignature();
        jws.setAlgorithmHeaderValue(algorithmIdentifier);
        jws.setKey(getSecretKey());
        jws.setKeyIdHeaderValue(getSecretKey().getAlgorithm());
        jws.setPayload(claims.toJson());

        // Serialize the cookie
        try {
            return jws.getCompactSerialization();
        } catch (final JoseException e) {
            throw new IllegalStateException("Unable to encode cookie: " + e, e);
        }
    }