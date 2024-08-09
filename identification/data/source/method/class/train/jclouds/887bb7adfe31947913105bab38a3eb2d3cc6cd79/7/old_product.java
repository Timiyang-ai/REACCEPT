@Deprecated
   public TemplateOptions installPrivateKey(String privateKey) {
      checkArgument(checkNotNull(privateKey, "privateKey").startsWith("-----BEGIN RSA PRIVATE KEY-----"),
               "key should start with -----BEGIN RSA PRIVATE KEY-----");
      Payload payload = newStringPayload(privateKey);
      payload.setContentType("text/plain");
      return installPrivateKey(payload);
   }