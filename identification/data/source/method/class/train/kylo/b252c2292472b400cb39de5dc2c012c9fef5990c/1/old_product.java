@Nonnull
    @Override
    protected String[] decodeCookie(@Nonnull final String cookie) throws InvalidCookieException {
        // Build the JWT parser
        final JwtConsumer consumer = new JwtConsumerBuilder()
            .setEvaluationTime(NumericDate.fromMilliseconds(DateTimeUtils.currentTimeMillis()))
            .setVerificationKey(getSecretKey())
            .build();

        // Parse the cookie
        final String user;
        final List<String> groups;

        try {
            final JwtClaims claims = consumer.processToClaims(cookie);
            user = claims.getSubject();
            groups = claims.getStringListClaimValue(GROUPS);
        } catch (final InvalidJwtException e) {
            throw new InvalidCookieException("JWT cookie is invalid: " + e);
        } catch (final MalformedClaimException e) {
            throw new InvalidCookieException("JWT cookie is malformed: " + cookie);
        }

        if (StringUtils.isBlank(user)) {
            throw new InvalidCookieException("Missing user in JWT cookie: " + cookie);
        }

        // Build the token array
        final Stream<String> userStream = Stream.of(user);
        final Stream<String> groupStream = groups.stream();
        return Stream.concat(userStream, groupStream).toArray(String[]::new);
    }