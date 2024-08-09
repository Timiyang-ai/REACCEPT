@Nullable
    public PaymentMethod createPaymentMethodSynchronous(
            @NonNull PaymentMethodCreateParams paymentMethodCreateParams,
            @NonNull String publishableKey)
            throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        return mApiHandler.createPaymentMethod(paymentMethodCreateParams,
                RequestOptions.createForApi(publishableKey, mStripeAccount));
    }