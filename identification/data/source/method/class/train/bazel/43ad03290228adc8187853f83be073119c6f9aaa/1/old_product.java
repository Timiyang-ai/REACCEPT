public Fingerprint addPath(Path input) {
    addStringLatin1(input.getPathString());
    return this;
  }