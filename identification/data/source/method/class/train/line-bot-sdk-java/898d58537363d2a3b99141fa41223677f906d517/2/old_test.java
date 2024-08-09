    @Test
    public void generateSignature() throws Exception {
        LineSignatureValidator lineSignatureValidator = new LineSignatureValidator(
                channelSecret.getBytes(StandardCharsets.UTF_8));

        String httpRequestBody = "{}";
        byte[] headerSignature = lineSignatureValidator
                .generateSignature(httpRequestBody.getBytes(StandardCharsets.UTF_8));

        assertThat(Base64Utils.encodeToString(headerSignature))
                .isEqualTo("3q8QXTAGaey18yL8FWTqdVlbMr6hcuNvM4tefa0o9nA=");
    }