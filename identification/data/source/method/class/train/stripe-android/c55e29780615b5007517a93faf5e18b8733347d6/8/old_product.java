@Nullable
    public PaymentMethod createPaymentMethodSynchronous(
            @NonNull PaymentMethodCreateParams paymentMethodCreateParams,
            @NonNull String publishableKey)
            throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        return StripeApiHandler.createPaymentMethod(paymentMethodCreateParams, mContext,
                publishableKey, mStripeAccount, mLoggingResponseListener);
    }