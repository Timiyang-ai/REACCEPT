public static PackageElement asPackage(Element element) {
    return element.accept(PACKAGE_ELEMENT_VISITOR, null);
  }