public static PresentationLibraryElement createElementInstance(TapestryLibrary library, IJavaClassType elementClass, TapestryProject project)
    throws NotTapestryElementException {
    switch (getElementType(elementClass, library.getBasePackage())) {
      case COMPONENT:
        return new TapestryComponent(library, elementClass, project);
      case PAGE:
        return new Page(library, elementClass, project);
      case MIXIN:
        return new Mixin(library, elementClass, project);
      default:
        throw new NotTapestryElementException(elementClass.getFullyQualifiedName() + " is not a Tapestry class.");
    }
  }