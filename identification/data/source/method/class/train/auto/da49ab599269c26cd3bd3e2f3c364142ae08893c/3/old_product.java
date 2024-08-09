public static VariableElement asVariable(Element element) {
    return element.accept(VARIABLE_ELEMENT_VISITOR, null);
  }