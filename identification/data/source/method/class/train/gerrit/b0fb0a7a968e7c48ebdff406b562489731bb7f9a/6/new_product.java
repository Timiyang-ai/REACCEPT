public SafeHtmlBuilder append(StringBuilder in) {
    if (in != null) {
      append(in.toString());
    }
    return this;
  }