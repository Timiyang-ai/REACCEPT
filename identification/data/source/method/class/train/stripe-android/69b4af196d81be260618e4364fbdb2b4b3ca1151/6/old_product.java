public void createPiiToken(
            @NonNull final String personalId,
            @NonNull @Size(min = 1) final String publishableKey,
            @Nullable final Executor executor,
            @NonNull final TokenCallback callback) {
        createTokenFromParams(
                createPersonalIdTokenParams(personalId),
                publishableKey,
                Token.TYPE_PII,
                executor,
                callback);
    }