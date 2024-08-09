@Deprecated
   public TemplateOptions installPrivateKey(Payload privateKey) {
      try {
         return installPrivateKey(Utils.toStringAndClose(checkNotNull(privateKey, "privateKey").getInput()));
      } catch (IOException e) {
         Throwables.propagate(e);
         return this;
      }
   }