public static PackageAndClass fromString(String qualifiedName) {
    // TODO: move NamingUtils.* in PackageAndClass
    return new PackageAndClass(
        extractTargetJavaPackage(qualifiedName),
        extractTargetJavaClass(qualifiedName));
  }