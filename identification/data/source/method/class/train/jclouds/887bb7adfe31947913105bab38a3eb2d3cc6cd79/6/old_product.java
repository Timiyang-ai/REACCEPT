public TemplateOptions installPrivateKey(Payload privateKey) {
      this.privateKey = checkNotNull(privateKey, "privateKey");
      return this;
   }