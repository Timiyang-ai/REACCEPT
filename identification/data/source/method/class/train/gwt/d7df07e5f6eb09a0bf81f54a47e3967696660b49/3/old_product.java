public static void putAttribute(Statements writer,
      XMLElement elem, String name, String value) {
    if (!isDesignTime()) {
      return;
    }
    String path = elem.getDesignTimePath();
    String key = path + " " + name;
    writer.addStatement("dtAttributes.put(\"%s\", %s);", key, value);
  }