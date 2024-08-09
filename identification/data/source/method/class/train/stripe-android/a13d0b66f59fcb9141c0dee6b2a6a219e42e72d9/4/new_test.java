    @Test
    public void createCvcUpdateToken() {
        createStripe().createCvcUpdateToken("123",
                new ApiResultCallback<Token>() {
                    @Override
                    public void onSuccess(@NonNull Token result) {

                    }

                    @Override
                    public void onError(@NonNull Exception e) {

                    }
                });
    }