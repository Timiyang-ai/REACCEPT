@ExperimentalApi("https://github.com/grpc/grpc-java/issues/492")
  public Set<String> getAdvertisedMessageEncodings() {
    Set<String> advertisedDecompressors = new HashSet<String>(decompressors.size());
    for (Entry<String, DecompressorInfo> entry : decompressors.entrySet()) {
      if (entry.getValue().advertised) {
        advertisedDecompressors.add(entry.getKey());
      }
    }
    return Collections.unmodifiableSet(advertisedDecompressors);
  }