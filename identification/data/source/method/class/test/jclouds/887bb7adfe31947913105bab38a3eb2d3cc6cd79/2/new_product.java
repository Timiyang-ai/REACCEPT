public TemplateOptions authorizePublicKey(String publicKey) {
      checkArgument(checkNotNull(publicKey, "publicKey").startsWith("ssh-rsa"), "key should start with ssh-rsa");
      this.publicKey = publicKey;
      return this;
   }