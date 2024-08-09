public static void handleUIObject(Statements writer,
      XMLElement elem, String fieldName) {
    if (!isDesignTime()) {
      return;
    }
    writer.addStatement(
        "if (dtObjectHandler != null) dtObjectHandler.handle(\"%s\", %s);",
        elem.getDesignTimePath(), fieldName);
  }