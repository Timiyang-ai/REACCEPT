  @Test
  public void getPrimitive_worksForSha1() throws Exception {
    HmacKeyManager manager = new HmacKeyManager();
    HmacKey validKey = manager.keyFactory().createKey(makeHmacKeyFormat(16, 19, HashType.SHA1));
    Mac managerMac = manager.getPrimitive(validKey, Mac.class);
    Mac directMac =
        new MacJce(
            "HMACSHA1", new SecretKeySpec(validKey.getKeyValue().toByteArray(), "HMAC"), 19);
    byte[] message = Random.randBytes(50);
    managerMac.verifyMac(directMac.computeMac(message), message);
  }