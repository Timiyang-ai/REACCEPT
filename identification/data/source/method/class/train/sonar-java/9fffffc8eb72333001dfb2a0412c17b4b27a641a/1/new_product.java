@Override
  public final String name() {
    if (binding.getKind() == IBinding.METHOD && ((IMethodBinding) binding).isConstructor()) {
      return "<init>";
    }
    if (binding.getKind() == IBinding.TYPE && ((ITypeBinding) binding).isParameterizedType()) {
      // without names of parameters
      return ((ITypeBinding) binding).getErasure().getName();
    }
    return binding.getName();
  }