public Set<String> getKnownMessageEncodings() {
    return Collections.unmodifiableSet(decompressors.keySet());
  }