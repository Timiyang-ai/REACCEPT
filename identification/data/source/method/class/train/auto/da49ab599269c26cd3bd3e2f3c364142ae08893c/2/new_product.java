public static PackageElement asPackage(Element element) {
    return element.accept(PackageElementVisitor.INSTANCE, null);
  }