public SafeHtmlBuilder append(final StringBuilder in) {
    if (in != null) {
      append(in.toString());
    }
    return this;
  }