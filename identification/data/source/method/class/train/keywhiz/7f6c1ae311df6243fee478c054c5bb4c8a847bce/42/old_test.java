  @Test public void secretListing_success() throws Exception {
    // Listing without secret16
    assertThat(listing()).doesNotContain("secret16");

    // Sample secret
    create(CreateSecretRequestV2.builder()
        .name("secret16")
        .description("test secret 16")
        .content(encoder.encodeToString("supa secret16".getBytes(UTF_8)))
        .build());

    // Listing with secret16
    assertThat(listing()).contains("secret16");

    List<SanitizedSecret> secrets = listingV2();
    boolean found = false;
    for (SanitizedSecret s : secrets) {
      if (s.name().equals("secret16")) {
        found = true;
        assertThat(s.description()).isEqualTo("test secret 16");
      }
    }
    assertThat(found).isTrue();
  }