public boolean isAbsolute() {
    return parsed.valid && parsed.scheme != null;
  }