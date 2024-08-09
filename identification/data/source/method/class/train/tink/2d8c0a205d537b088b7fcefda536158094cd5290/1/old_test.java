  @Test
  public void getPrimitive() throws Exception {
    AesCtrKey key = factory.createKey(createFormat(14, 32));
    IndCpaCipher managerCipher = manager.getPrimitive(key, IndCpaCipher.class);
    IndCpaCipher directCipher = new AesCtrJceCipher(key.getKeyValue().toByteArray(), 14);

    byte[] plaintext = Random.randBytes(20);
    assertThat(directCipher.decrypt(managerCipher.encrypt(plaintext))).isEqualTo(plaintext);
  }