public static boolean exec(JsProgram program, JsBlock block, JsScope scope,
      boolean alwaysIntern) {

    Multiset<JsLiteral> occurrencesPerLiteral = null;
    if (!alwaysIntern) {
      occurrencesPerLiteral = computeOccurrenceCounts(block);
    }

    LiteralInterningVisitor v = new LiteralInterningVisitor(null, scope, alwaysIntern,
        occurrencesPerLiteral, INTERN_ALL);
    v.accept(block);

    createVars(program, block, v.variableNameForInternedLiteral.keySet(),
        v.variableNameForInternedLiteral);

    return v.didChange();
  }