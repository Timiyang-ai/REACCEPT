@Deprecated
    public void createPiiToken(
            @NonNull final String personalId,
            @NonNull @Size(min = 1) final String publishableKey,
            @Nullable final Executor executor,
            @NonNull final ApiResultCallback<Token> callback) {
        createTokenFromParams(
                new PiiTokenParams(personalId).toParamMap(),
                publishableKey,
                Token.TokenType.PII,
                executor,
                callback);
    }