@Nullable
    public PaymentMethod createPaymentMethodSynchronous(
            @NonNull PaymentMethodCreateParams paymentMethodCreateParams,
            @NonNull String publishableKey)
            throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        return mApiHandler.createPaymentMethod(paymentMethodCreateParams,
                ApiRequest.Options.create(publishableKey, mStripeAccountId));
    }