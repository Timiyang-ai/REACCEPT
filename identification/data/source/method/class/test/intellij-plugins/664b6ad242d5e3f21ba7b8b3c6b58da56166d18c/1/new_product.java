public IResource[] getMessageCatalog() {
    if (_messageCatalogCache != null && checkAllValidResources(_messageCatalogCache)) {
      return _messageCatalogCache;
    }

    String packageName = getElementClass().getFullyQualifiedName().substring(0, getElementClass().getFullyQualifiedName().lastIndexOf('.'));

    // Search in the classpath
    Collection<IResource> resources = getProject().getResourceFinder().findLocalizedClasspathResource(
      PathUtils.packageIntoPath(packageName, true) + PathUtils.getLastPathElement(getName()) + TapestryConstants.PROPERTIES_FILE_EXTENSION,
      true);

    if (resources.size() > 0) {
      List<IResource> catalogs = new ArrayList<>();

      for (IResource catalog : resources) {
        if (LocalizationUtils.unlocalizeFileName(catalog.getName())
          .equals(PathUtils.getLastPathElement(getName()) + TapestryConstants.PROPERTIES_FILE_EXTENSION)) {
          catalogs.add(catalog);
        }
      }

      _messageCatalogCache = catalogs.toArray(IResource.EMPTY_ARRAY);

      return _messageCatalogCache;
    }
    else {
      _messageCatalogCache = IResource.EMPTY_ARRAY;
    }

    return _messageCatalogCache;
  }