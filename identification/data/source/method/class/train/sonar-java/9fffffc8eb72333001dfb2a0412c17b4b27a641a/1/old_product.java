@Override
  public final String name() {
    if (binding.getKind() == IBinding.METHOD && ((IMethodBinding) binding).isConstructor()) {
      return "<init>";
    }
    return binding.getName();
  }