    @Test
    public void validateSignature() throws Exception {
        LineSignatureValidator lineSignatureValidator = new LineSignatureValidator(
                channelSecret.getBytes(StandardCharsets.UTF_8));

        String httpRequestBody = "{}";
        assertThat(lineSignatureValidator
                           .validateSignature(httpRequestBody.getBytes(StandardCharsets.UTF_8),
                                              "3q8QXTAGaey18yL8FWTqdVlbMr6hcuNvM4tefa0o9nA="))
                .isTrue();
        assertThat(lineSignatureValidator
                           .validateSignature(httpRequestBody.getBytes(StandardCharsets.UTF_8),
                                              "596359635963"))
                .isFalse();
    }