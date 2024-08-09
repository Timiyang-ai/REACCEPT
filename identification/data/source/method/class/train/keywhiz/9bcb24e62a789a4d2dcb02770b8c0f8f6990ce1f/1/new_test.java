  @Test
  public void partialUpdateSecret() throws Exception {
    when(secretController.getSecretById(secret.getId())).thenReturn(Optional.of(secret));

    PartialUpdateSecretRequestV2 req = PartialUpdateSecretRequestV2.builder()
        .description(secret.getDescription())
        .descriptionPresent(true)
        .metadata(ImmutableMap.of("owner", "keywhizAdmin"))
        .metadataPresent(true)
        .expiry(1487268151L)
        .expiryPresent(true)
        .type("test")
        .typePresent(true)
        .content(secret.getSecret())
        .contentPresent(true)
        .build();

    when(secretDAO.partialUpdateSecret(eq(secret.getName()), any(), eq(req))).thenReturn(
        secret.getId());

    Response response = resource.partialUpdateSecret(user, secret.getName(), req);

    assertThat(response.getStatus()).isEqualTo(201);
    assertThat(response.getMetadata().get(HttpHeaders.LOCATION))
        .containsExactly(new URI("/admin/secrets/" + secret.getName() + "/partialupdate"));
  }