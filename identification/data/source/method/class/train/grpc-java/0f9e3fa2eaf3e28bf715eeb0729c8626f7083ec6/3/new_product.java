@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1704")
  public Set<String> getAdvertisedMessageEncodings() {
    Set<String> advertisedDecompressors = new HashSet<>(decompressors.size());
    for (Entry<String, DecompressorInfo> entry : decompressors.entrySet()) {
      if (entry.getValue().advertised) {
        advertisedDecompressors.add(entry.getKey());
      }
    }
    return Collections.unmodifiableSet(advertisedDecompressors);
  }