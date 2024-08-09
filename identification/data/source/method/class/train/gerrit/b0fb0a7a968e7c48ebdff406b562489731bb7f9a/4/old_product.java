public SafeHtmlBuilder append(final String in) {
    if (in != null) {
      impl.escapeStr(this, in);
    }
    return this;
  }