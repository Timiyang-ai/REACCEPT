@Deprecated
   public TemplateOptions authorizePublicKey(Payload publicKey) {
      try {
         return authorizePublicKey(Strings2.toStringAndClose(checkNotNull(publicKey, "publicKey").getInput()));
      } catch (IOException e) {
         Throwables.propagate(e);
         return this;
      }
   }