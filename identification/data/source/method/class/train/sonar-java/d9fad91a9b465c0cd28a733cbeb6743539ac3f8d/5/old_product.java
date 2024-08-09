@Override
  public final Symbol owner() {
    switch (binding.getKind()) {
      case IBinding.TYPE: {
        ITypeBinding typeBinding = (ITypeBinding) binding;
        IMethodBinding declaringMethod = typeBinding.getDeclaringMethod();
        if (declaringMethod != null) { // local type
          return sema.methodSymbol(declaringMethod);
        }
        ITypeBinding declaringClass = typeBinding.getDeclaringClass();
        if (declaringClass != null) { // member type
          return sema.typeSymbol(declaringClass);
        }
        // top-level type
        return new JSymbol(sema, typeBinding.getPackage()) {
        };
      }
      case IBinding.METHOD: {
        IMethodBinding methodBinding = (IMethodBinding) binding;
        return sema.typeSymbol(methodBinding.getDeclaringClass());
      }
      case IBinding.VARIABLE: {
        IVariableBinding variableBinding = (IVariableBinding) binding;
        IMethodBinding declaringMethod = variableBinding.getDeclaringMethod();
        if (declaringMethod != null) { // local variable
          return sema.methodSymbol(declaringMethod);
        }
        ITypeBinding declaringClass = variableBinding.getDeclaringClass();
        if (declaringClass != null) { // field
          return sema.typeSymbol(declaringClass);
        }
        // FIXME
        // variable declaration in a static or instance initializer
        // or local variable declaration in recovered method
        // or array.length
        return Symbols.unknownSymbol;
      }
      default:
        throw new IllegalStateException("Kind: " + binding.getKind());
    }
  }