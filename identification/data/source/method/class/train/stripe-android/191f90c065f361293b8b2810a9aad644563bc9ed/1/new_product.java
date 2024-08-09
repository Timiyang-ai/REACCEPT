public void createBankAccountToken(
            @NonNull final BankAccount bankAccount,
            @NonNull @Size(min = 1) final String publishableKey,
            @Nullable final Executor executor,
            @NonNull final TokenCallback callback) {
        if (bankAccount == null) {
            throw new RuntimeException(
                    "Required parameter: 'bankAccount' is requred to create a token");
        }

        createTokenFromParams(
                mStripeNetworkUtils.hashMapFromBankAccount(bankAccount),
                publishableKey,
                Token.TYPE_BANK_ACCOUNT,
                executor,
                callback);
    }