@Override
  public final Symbol owner() {
    switch (binding.getKind()) {
      case IBinding.PACKAGE:
        return Symbols.rootPackage;
      case IBinding.TYPE:
        return typeOwner((ITypeBinding) binding);
      case IBinding.METHOD:
        return methodOwner((IMethodBinding) binding);
      case IBinding.VARIABLE:
        return variableOwner((IVariableBinding) binding);
      default:
        throw new IllegalStateException("Unexpected binding Kind: " + binding.getKind());
    }
  }