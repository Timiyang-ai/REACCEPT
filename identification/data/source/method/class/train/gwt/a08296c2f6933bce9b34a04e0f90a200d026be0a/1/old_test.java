  private Map<JsName, JsLiteral> exec(JsProgram program) {
    // Prerequisite: resolve name references.
    JsSymbolResolver.exec(program);
    return JsLiteralInterner.exec(null, program, JsLiteralInterner.INTERN_ALL);
  }