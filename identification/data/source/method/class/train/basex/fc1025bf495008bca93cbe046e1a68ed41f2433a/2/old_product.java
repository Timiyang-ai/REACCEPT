public boolean selfRecursive() {
    return !expr.accept(new ASTVisitor() {
      @Override
      public boolean funcCall(final StaticFuncCall call) {
        return call.func != StaticFunc.this;
      }

      @Override
      public boolean inlineFunc(final Scope sub) {
        return sub.visit(this);
      }
    });
  }