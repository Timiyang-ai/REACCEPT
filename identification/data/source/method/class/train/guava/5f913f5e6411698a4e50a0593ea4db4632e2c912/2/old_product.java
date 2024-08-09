static <D extends GenericDeclaration> TypeVariable<D> newArtificialTypeVariable(
      D declaration, String name, Type... bounds) {
    return new TypeVariableImpl<D>(
        declaration,
        name,
        (bounds.length == 0)
            ? new Type[] { Object.class }
            : bounds);
  }