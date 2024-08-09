  @Test
  public void getPrimitive() throws Exception {
    AesCtrHmacAeadKey key =
        factory.createKey(
            createKeyFormat()
                .setHmacKeyFormat(
                    createHmacKeyFormat().setParams(createHmacParams().setHash(HashType.SHA512)))
                .build());
    Aead managerAead = manager.getPrimitive(key, Aead.class);
    Aead directAead =
        EncryptThenAuthenticate.newAesCtrHmac(
            key.getAesCtrKey().getKeyValue().toByteArray(),
            key.getAesCtrKey().getParams().getIvSize(),
            "HMACSHA512",
            key.getHmacKey().getKeyValue().toByteArray(),
            key.getHmacKey().getParams().getTagSize());

    byte[] plaintext = Random.randBytes(20);
    byte[] associatedData = Random.randBytes(20);
    assertThat(directAead.decrypt(managerAead.encrypt(plaintext, associatedData), associatedData))
        .isEqualTo(plaintext);
  }