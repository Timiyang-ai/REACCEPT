public static ExecutableElement asExecutable(Element element) {
    return element.accept(ExecutableElementVisitor.INSTANCE, null);
  }