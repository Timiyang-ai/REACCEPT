    @Test
    public void createPaymentMethod_withCardToken()
            throws StripeException {
        final Stripe stripe = createStripe();
        final Token token = stripe.createCardTokenSynchronous(CARD);

        final PaymentMethodCreateParams paymentMethodCreateParams =
                PaymentMethodCreateParams.create(
                        PaymentMethodCreateParams.Card.create(token.getId()),
                        null);
        final PaymentMethod createdPaymentMethod = stripe.createPaymentMethodSynchronous(
                paymentMethodCreateParams);
        assertNotNull(createdPaymentMethod);
        assertNotNull(createdPaymentMethod.card);
        assertEquals("visa", createdPaymentMethod.card.brand);
        assertEquals("4242", createdPaymentMethod.card.last4);
    }