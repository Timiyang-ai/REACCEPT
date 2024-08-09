@Nullable
  public static Decompressor lookupDecompressor(String messageEncoding) {
    return INSTANCE.internalLookupDecompressor(messageEncoding);
  }