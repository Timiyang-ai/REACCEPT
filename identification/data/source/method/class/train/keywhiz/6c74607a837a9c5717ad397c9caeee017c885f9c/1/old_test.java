  @Test public void createOrUpdateSecret() throws Exception {
    CreateOrUpdateSecretRequestV2 request = CreateOrUpdateSecretRequestV2.builder()
        .content(encoder.encodeToString("supa secret".getBytes(UTF_8)))
        .description("desc")
        .metadata(ImmutableMap.of("owner", "root", "mode", "0440"))
        .type("password")
        .build();
    Response httpResponse = createOrUpdate(request, "secret3");
    assertThat(httpResponse.code()).isEqualTo(201);
    URI location = URI.create(httpResponse.header(LOCATION));
    assertThat(location.getPath()).isEqualTo("/automation/v2/secrets/secret3");

    httpResponse = createOrUpdate(request, "secret3");
    assertThat(httpResponse.code()).isEqualTo(201);
    location = URI.create(httpResponse.header(LOCATION));
    assertThat(location.getPath()).isEqualTo("/automation/v2/secrets/secret3");
  }