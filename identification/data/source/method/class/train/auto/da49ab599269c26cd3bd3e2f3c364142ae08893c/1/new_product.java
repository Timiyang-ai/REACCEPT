public static TypeElement asType(Element element) {
    return element.accept(TypeElementVisitor.INSTANCE, null);
  }