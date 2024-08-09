@Deprecated
   public TemplateOptions installPrivateKey(Payload privateKey) {
      try {
         return installPrivateKey(Strings2.toStringAndClose(checkNotNull(privateKey, "privateKey").getInput()));
      } catch (IOException e) {
         Throwables.propagate(e);
         return this;
      }
   }