@Nullable
  public Decompressor lookupDecompressor(String messageEncoding) {
    DecompressorInfo info = decompressors.get(messageEncoding);
    return info != null ? info.decompressor : null;
  }