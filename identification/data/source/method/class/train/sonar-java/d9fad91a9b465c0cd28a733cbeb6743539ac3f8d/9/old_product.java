@Nullable
  @Override
  public final TypeSymbol enclosingClass() {
    switch (binding.getKind()) {
      case IBinding.PACKAGE:
        return null;
      case IBinding.TYPE:
        return typeEnclosingClass((ITypeBinding) binding);
      case IBinding.METHOD:
        return methodEnclosingClass((IMethodBinding) binding);
      case IBinding.VARIABLE:
        return variableEnclosingClass((IVariableBinding) binding);
      default:
        throw new IllegalStateException("Unexpected binding Kind: " + binding.getKind());
    }
  }