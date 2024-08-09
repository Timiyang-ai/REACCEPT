@Override
  protected MethodMetadata parseAndValidateMetadata(Class<?> targetType, Method method) {
    MethodMetadata md = super.parseAndValidateMetadata(targetType, method);
    Path path = targetType.getAnnotation(Path.class);
    if (path != null) {
      String pathValue = emptyToNull(path.value());
      checkState(pathValue != null, "Path.value() was empty on type %s", targetType.getName());
      if (!pathValue.startsWith("/")) {
        pathValue = "/" + pathValue;
      }
      if (pathValue.endsWith("/")) {
          // Strip off any trailing slashes, since the template has already had slashes appropriately added
          pathValue = pathValue.substring(0, pathValue.length()-1);
      }
      md.template().insert(0, pathValue);
    }
    return md;
  }