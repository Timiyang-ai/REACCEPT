  private static <D extends GenericDeclaration> TypeVariable<D> withBounds(
      TypeVariable<D> typeVariable, Type... bounds) {
    return Types.newArtificialTypeVariable(
        typeVariable.getGenericDeclaration(), typeVariable.getName(), bounds);
  }