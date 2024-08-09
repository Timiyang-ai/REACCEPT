protected String getElementNameFromClass(String libraryRootPackage) throws NotTapestryElementException {
    if (!_class.isPublic() || !_class.hasDefaultConstructor()) {
      throw new NotTapestryElementException(_class.getFullyQualifiedName() + " is not a valid Tapestry class.");
    }

    if (libraryRootPackage == null) {
      throw new NotTapestryElementException(_class.getFullyQualifiedName() + " is not a valid Tapestry class.");
    }

    String elementClassFqn = _class.getFullyQualifiedName();
    String elementName;

    elementName = elementClassFqn.substring(libraryRootPackage.length() + 1);
    if (elementName.startsWith(TapestryConstants.COMPONENTS_PACKAGE)) {
      elementName = PathUtils.packageIntoPath(elementName.substring(TapestryConstants.COMPONENTS_PACKAGE.length() + 1), false);
    }
    else if (elementName.startsWith(TapestryConstants.BASE_PACKAGE)) {
      elementName = PathUtils.packageIntoPath(elementName.substring(TapestryConstants.BASE_PACKAGE.length() + 1), false);
    }
    else if (elementName.startsWith(TapestryConstants.PAGES_PACKAGE)) {
      elementName = PathUtils.packageIntoPath(elementName.substring(TapestryConstants.PAGES_PACKAGE.length() + 1), false);
    }
    else if (elementName.startsWith(TapestryConstants.MIXINS_PACKAGE)) {
      elementName = PathUtils.packageIntoPath(elementName.substring(TapestryConstants.MIXINS_PACKAGE.length() + 1), false);
    }
    else {
      throw new NotTapestryElementException(_class.getFullyQualifiedName() + " is not under a Tapestry base package.");
    }

    return elementName;
  }