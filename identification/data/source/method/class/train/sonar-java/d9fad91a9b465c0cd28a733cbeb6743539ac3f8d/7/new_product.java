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
        return sema.packageSymbol(typeBinding.getPackage());
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
        Tree node = sema.declarations.get(variableBinding);
        if (node == null) {
          // array.length
          return Symbols.unknownSymbol;
        }
        do {
          node = node.parent();
          switch (node.kind()) {
            case CLASS:
            case ENUM:
              // variable declaration in a static or instance initializer
              return sema.typeSymbol(((ClassTreeImpl) node).typeBinding);
            case METHOD:
            case CONSTRUCTOR:
              // local variable declaration in recovered method
              // and recovered methods do not have bindings
              return Symbols.unknownMethodSymbol;
          }
        } while (true);
      }
      default:
        throw new IllegalStateException("Kind: " + binding.getKind());
    }
  }