public static VariableElement asVariable(Element element) {
    return element.accept(VariableElementVisitor.INSTANCE, null);
  }