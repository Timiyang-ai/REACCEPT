@Deprecated
   public TemplateOptions authorizePublicKey(Payload publicKey) {
      try {
         return authorizePublicKey(Utils.toStringAndClose(checkNotNull(publicKey, "publicKey").getInput()));
      } catch (IOException e) {
         Throwables.propagate(e);
         return this;
      }
   }