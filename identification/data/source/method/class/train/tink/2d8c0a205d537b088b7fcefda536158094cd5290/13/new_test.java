  @Test
  public void getPrimitive() throws Exception {
    AesEaxKey key = factory.createKey(createKeyFormat(32, 16));
    Aead managerAead = manager.getPrimitive(key, Aead.class);
    Aead directAead = new AesEaxJce(key.getKeyValue().toByteArray(), key.getParams().getIvSize());

    byte[] plaintext = Random.randBytes(20);
    byte[] associatedData = Random.randBytes(20);
    assertThat(directAead.decrypt(managerAead.encrypt(plaintext, associatedData), associatedData))
        .isEqualTo(plaintext);
  }