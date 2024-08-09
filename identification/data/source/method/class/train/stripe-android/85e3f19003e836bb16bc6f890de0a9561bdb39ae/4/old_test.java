    @NonNull
    private Source createSource() throws StripeException {
        final Stripe stripe = createStripe();
        final SourceParams params = SourceParams.createCardParams(
                Card.create(VALID_VISA_NO_SPACES, 12, 2050, "123")
        );

        final Source cardSource = stripe.createSourceSynchronous(params);

        assertNotNull(cardSource);
        assertNotNull(cardSource.getId());
        SourceParams threeDParams = SourceParams.createThreeDSecureParams(
                5000L,
                "brl",
                "example://return",
                cardSource.getId()
        );

        final Map<String, String> metamap = new HashMap<String, String>() {{
            put("dimensions", "three");
            put("type", "beach ball");
        }};
        threeDParams.setMetaData(metamap);

        final Source source = stripe.createSourceSynchronous(threeDParams);
        assertNotNull(source);
        return source;
    }