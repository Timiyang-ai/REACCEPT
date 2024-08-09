public String makeAppSecretProof() {
    try {
      byte[] key = this.appSecret.getBytes();
      SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(signingKey);
      byte[] raw = mac.doFinal(this.accessToken.getBytes());
      byte[] hex = encodeHex(raw);
      String out = new String(hex, "UTF-8");
      return out;
    } catch (NoSuchAlgorithmException e) {
      System.out.println(e);
    } catch (InvalidKeyException e) {
      System.out.println(e);
    } catch (UnsupportedEncodingException e) {
      System.out.println(e);
    }
    throw new NullPointerException("AppSecretProof creation has failed");
  }