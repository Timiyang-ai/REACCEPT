public static PackageAndClass fromString(String qualifiedName) {
    return new PackageAndClass(NamingUtils.extractTargetJavaPackage(qualifiedName), NamingUtils.extractTargetJavaClass(qualifiedName));
  }