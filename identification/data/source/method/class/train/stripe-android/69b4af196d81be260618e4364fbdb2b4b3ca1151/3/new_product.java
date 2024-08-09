public void createPiiToken(
            @NonNull final String personalId,
            @NonNull final ApiResultCallback<Token> callback) {
        createTokenFromParams(
                new PiiTokenParams(personalId).toParamMap(),
                Token.TokenType.PII,
                callback
        );
    }