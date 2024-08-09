public SafeHtmlBuilder append(StringBuffer in) {
    if (in != null) {
      append(in.toString());
    }
    return this;
  }