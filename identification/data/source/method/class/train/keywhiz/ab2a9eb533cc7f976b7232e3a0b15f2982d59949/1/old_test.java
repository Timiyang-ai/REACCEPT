  @Test
  public void createOrUpdateSecret() throws Exception {
    when(secretController.getSecretById(secret.getId())).thenReturn(Optional.of(secret));

    SecretController.SecretBuilder secretBuilder = mock(SecretController.SecretBuilder.class);
    when(secretController.builder(secret.getName(), secret.getSecret(), user.getName(), 0))
        .thenReturn(secretBuilder);
    when(secretBuilder.withDescription(any())).thenReturn(secretBuilder);
    when(secretBuilder.withMetadata(any())).thenReturn(secretBuilder);
    when(secretBuilder.withType(any())).thenReturn(secretBuilder);
    when(secretBuilder.createOrUpdate()).thenReturn(secret);

    CreateOrUpdateSecretRequestV2 req = CreateOrUpdateSecretRequestV2.builder()
        .description(secret.getDescription())
        .content(secret.getSecret())
        .build();

    Response response = resource.createOrUpdateSecret(user, secret.getName(), req);

    assertThat(response.getStatus()).isEqualTo(201);
    assertThat(response.getMetadata().get(HttpHeaders.LOCATION))
        .containsExactly(new URI("/admin/secrets/" + secret.getName()));
  }