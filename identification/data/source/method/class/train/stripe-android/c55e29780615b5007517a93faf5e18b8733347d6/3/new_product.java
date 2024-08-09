@Nullable
    public PaymentMethod createPaymentMethodSynchronous(
            @NonNull PaymentMethodCreateParams paymentMethodCreateParams)
            throws APIException, AuthenticationException, InvalidRequestException,
            APIConnectionException {
        return mStripeRepository.createPaymentMethod(paymentMethodCreateParams,
                ApiRequest.Options.create(mDefaultPublishableKey, mStripeAccountId));
    }