@Override
    public String reflectionName() {
      return isDefaultPackage(packageName)
          ? simpleName
          : String.join(".", Arrays.asList(packageName, simpleName));
    }