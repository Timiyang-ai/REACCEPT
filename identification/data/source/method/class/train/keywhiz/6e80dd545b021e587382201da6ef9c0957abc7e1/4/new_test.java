  @Test
  public void backfillExpirationTest() throws Exception {
    byte[] certs = Resources.toByteArray(Resources.getResource("fixtures/expiring-certificates.crt"));
    byte[] pubring = Resources.toByteArray(Resources.getResource("fixtures/expiring-pubring.gpg"));
    byte[] p12 = Resources.toByteArray(Resources.getResource("fixtures/expiring-keystore.p12"));
    byte[] jceks = Resources.toByteArray(Resources.getResource("fixtures/expiring-keystore.jceks"));

    create(CreateSecretRequestV2.builder()
        .name("certificate-chain.crt")
        .content(encoder.encodeToString(certs))
        .build());

    create(CreateSecretRequestV2.builder()
        .name("public-keyring.gpg")
        .content(encoder.encodeToString(pubring))
        .build());

    create(CreateSecretRequestV2.builder()
        .name("keystore.p12")
        .content(encoder.encodeToString(p12))
        .build());

    create(CreateSecretRequestV2.builder()
        .name("keystore.jceks")
        .content(encoder.encodeToString(jceks))
        .build());

    Response response = backfillExpiration("certificate-chain.crt", ImmutableList.of());
    assertThat(response.isSuccessful()).isTrue();

    response = backfillExpiration("public-keyring.gpg", ImmutableList.of());
    assertThat(response.isSuccessful()).isTrue();

    response = backfillExpiration("keystore.p12", ImmutableList.of("password"));
    assertThat(response.isSuccessful()).isTrue();

    response = backfillExpiration("keystore.jceks", ImmutableList.of("password"));
    assertThat(response.isSuccessful()).isTrue();

    SecretDetailResponseV2 details = lookup("certificate-chain.crt");
    assertThat(details.expiry()).isEqualTo(1501533950);

    details = lookup("public-keyring.gpg");
    assertThat(details.expiry()).isEqualTo(1536442365);

    details = lookup("keystore.p12");
    assertThat(details.expiry()).isEqualTo(1681596851);

    details = lookup("keystore.jceks");
    assertThat(details.expiry()).isEqualTo(1681596851);
  }