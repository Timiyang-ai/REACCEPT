public SafeHtmlBuilder append(String in) {
    if (in != null) {
      impl.escapeStr(this, in);
    }
    return this;
  }