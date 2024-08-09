  private JSDocInfo parse(String comment, String... warnings) {
    return parse(comment, JsDocParsing.TYPES_ONLY, warnings);
  }