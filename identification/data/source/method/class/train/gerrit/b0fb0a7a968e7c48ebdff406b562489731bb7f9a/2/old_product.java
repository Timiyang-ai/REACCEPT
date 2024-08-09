public SafeHtmlBuilder append(final Object in) {
    if (in != null) {
      append(in.toString());
    }
    return this;
  }