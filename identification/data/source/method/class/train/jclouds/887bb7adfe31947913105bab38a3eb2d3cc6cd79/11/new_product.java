public TemplateOptions installPrivateKey(String privateKey) {
      checkArgument(checkNotNull(privateKey, "privateKey").startsWith("-----BEGIN RSA PRIVATE KEY-----"),
               "key should start with -----BEGIN RSA PRIVATE KEY-----");
      this.privateKey = privateKey;
      return this;
   }