static <D extends GenericDeclaration> TypeVariable<D> newArtificialTypeVariable(
      D declaration, String name, Type... bounds) {
    return newTypeVariableImpl(
        declaration,
        name,
        (bounds.length == 0)
            ? new Type[] { Object.class }
            : bounds);
  }