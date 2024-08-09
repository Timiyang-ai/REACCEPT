@Test
    public void setReadOnly() {

        lockUnlockRunner((expected) -> {
            // read-only setting
            client.addConfigurationSettingWithResponse(expected, Context.NONE);
            client.setReadOnly(expected.getKey(), expected.getLabel(), true);

            // unsuccessfully delete
            assertRestException(() ->
                client.deleteConfigurationSettingWithResponse(expected, false, Context.NONE),
                HttpResponseException.class, 409);
        });
    }