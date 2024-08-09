public void createPiiToken(
            @NonNull final String personalId,
            @NonNull final ApiResultCallback<Token> callback) {
        createTokenFromParams(
                new PiiTokenParams(personalId).toParamMap(),
                mDefaultPublishableKey,
                Token.TokenType.PII,
                null,
                callback);
    }