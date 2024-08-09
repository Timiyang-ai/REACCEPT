@Deprecated
    public void createBankAccountToken(
            @NonNull final BankAccount bankAccount,
            @NonNull @Size(min = 1) final String publishableKey,
            @Nullable final Executor executor,
            @NonNull final ApiResultCallback<Token> callback) {
        final Map<String, Object> params = bankAccount.toParamMap();
        params.putAll(mStripeNetworkUtils.createUidParams());
        createTokenFromParams(
                params,
                publishableKey,
                Token.TokenType.BANK_ACCOUNT,
                executor,
                callback
        );
    }