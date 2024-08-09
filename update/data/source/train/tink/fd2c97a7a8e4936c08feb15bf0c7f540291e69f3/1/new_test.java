@Test
  public void testGetPublicKeysetHandle() throws Exception {
    KeysetHandle privateHandle = KeysetHandle.generateNew(
        SignatureKeyTemplates.ECDSA_P256);
    KeyData privateKeyData = privateHandle.getKeyset().getKey(0).getKeyData();
    EcdsaPrivateKey privateKey = EcdsaPrivateKey.parseFrom(privateKeyData.getValue());
    KeysetHandle publicHandle = privateHandle.getPublicKeysetHandle();
    assertEquals(1, publicHandle.getKeyset().getKeyCount());
    assertEquals(privateHandle.getKeyset().getPrimaryKeyId(),
        publicHandle.getKeyset().getPrimaryKeyId());
    KeyData publicKeyData = publicHandle.getKeyset().getKey(0).getKeyData();
    assertEquals(EcdsaVerifyKeyManager.TYPE_URL, publicKeyData.getTypeUrl());
    assertEquals(KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, publicKeyData.getKeyMaterialType());
    assertArrayEquals(privateKey.getPublicKey().toByteArray(),
        publicKeyData.getValue().toByteArray());

    PublicKeySign signer = PublicKeySignFactory.getPrimitive(privateHandle);
    PublicKeyVerify verifier = PublicKeyVerifyFactory.getPrimitive(publicHandle);
    byte[] message = Random.randBytes(20);
    try {
      verifier.verify(signer.sign(message), message);
    } catch (GeneralSecurityException e) {
      fail("Should not fail: " + e);
    }
  }