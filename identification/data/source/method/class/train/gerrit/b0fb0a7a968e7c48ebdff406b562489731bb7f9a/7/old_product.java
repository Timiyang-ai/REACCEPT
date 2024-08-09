public SafeHtmlBuilder append(final StringBuffer in) {
    if (in != null) {
      append(in.toString());
    }
    return this;
  }