@Deprecated
    @Nullable
    public PaymentMethod createPaymentMethodSynchronous(
            @NonNull PaymentMethodCreateParams paymentMethodCreateParams,
            @NonNull String publishableKey)
            throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        return mStripeRepository.createPaymentMethod(paymentMethodCreateParams,
                ApiRequest.Options.create(publishableKey, mStripeAccountId));
    }