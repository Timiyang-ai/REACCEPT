public static void addDeclarations(IndentedWriter w) {
    if (!isDesignTime()) {
      return;
    }
    w.write("public static interface DTObjectHandler {void handle(String path, Object object);}");
    w.write("public DTObjectHandler dtObjectHandler;");
    w.write("public final java.util.Map dtAttributes = new java.util.HashMap();");
  }