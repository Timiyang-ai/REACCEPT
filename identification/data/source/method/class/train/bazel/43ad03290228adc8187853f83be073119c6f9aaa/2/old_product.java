public Fingerprint addPath(PathFragment input) {
    addStringLatin1(input.getPathString());
    return this;
  }