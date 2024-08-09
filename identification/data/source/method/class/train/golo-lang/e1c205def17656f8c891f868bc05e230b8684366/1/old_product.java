public static PackageAndClass fromString(String qualifiedName) {
    return new PackageAndClass(
        extractTargetJavaPackage(qualifiedName),
        extractTargetJavaClass(qualifiedName));
  }