    @Test
    public void createPaymentMethodSynchronous_withCard()
            throws StripeException {
        final PaymentMethodCreateParams paymentMethodCreateParams =
                PaymentMethodCreateParamsFixtures.DEFAULT_CARD;
        final Stripe stripe = createStripe();
        final PaymentMethod createdPaymentMethod = stripe.createPaymentMethodSynchronous(
                paymentMethodCreateParams);
        assertNotNull(createdPaymentMethod);
        assertEquals(PaymentMethodCreateParamsFixtures.BILLING_DETAILS,
                createdPaymentMethod.billingDetails);
        assertNotNull(createdPaymentMethod.card);
        assertEquals("4242", createdPaymentMethod.card.last4);
    }