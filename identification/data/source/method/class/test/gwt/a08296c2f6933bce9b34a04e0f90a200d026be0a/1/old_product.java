public static boolean exec(JsProgram program, JsBlock block, JsScope scope,
      boolean alwaysIntern) {
    LiteralInterningVisitor v = new LiteralInterningVisitor(null, scope, alwaysIntern ? null :
        computeOccurrenceCounts(block), INTERN_ALL);
    v.accept(block);

    createVars(program, block, v.toCreate.keySet(), v.toCreate);

    return v.didChange();
  }