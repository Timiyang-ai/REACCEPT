@Nullable
  @Override
  public final TypeSymbol enclosingClass() {
    switch (binding.getKind()) {
      case IBinding.PACKAGE:
        return null;
      case IBinding.TYPE: {
        ITypeBinding typeBinding = (ITypeBinding) binding;
        ITypeBinding declaringClass = typeBinding.getDeclaringClass();
        if (declaringClass != null) { // nested (member or local) type
          return sema.typeSymbol(declaringClass);
        }
        // top-level type
        return (TypeSymbol) this;
      }
      case IBinding.METHOD: {
        ITypeBinding declaringClass = ((IMethodBinding) binding).getDeclaringClass();
        return sema.typeSymbol(declaringClass);
      }
      case IBinding.VARIABLE: {
        IVariableBinding variableBinding = (IVariableBinding) this.binding;
        ITypeBinding declaringClass = variableBinding.getDeclaringClass();
        if (declaringClass != null) { // field
          return sema.typeSymbol(declaringClass);
        }
        IMethodBinding declaringMethod = variableBinding.getDeclaringMethod();
        if (declaringMethod != null) { // local variable
          return sema.typeSymbol(declaringMethod.getDeclaringClass());
        }
        Tree node = sema.declarations.get(variableBinding);
        if (node == null) {
          // array.length
          return Symbols.unknownSymbol;
        }
        do {
          node = node.parent();
        } while (node.kind() != Tree.Kind.CLASS);
        // variable declaration in a static or instance initializer
        // or local variable declaration in recovered method
        return sema.typeSymbol(((ClassTreeImpl) node).typeBinding);
      }
      default:
        throw new IllegalStateException("Kind: " + binding.getKind());
    }
  }