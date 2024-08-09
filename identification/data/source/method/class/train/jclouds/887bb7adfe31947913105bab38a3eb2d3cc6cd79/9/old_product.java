@Deprecated
   public TemplateOptions authorizePublicKey(String publicKey) {
      checkArgument(checkNotNull(publicKey, "publicKey").startsWith("ssh-rsa"), "key should start with ssh-rsa");
      Payload payload = newStringPayload(publicKey);
      payload.setContentType("text/plain");
      return authorizePublicKey(payload);
   }