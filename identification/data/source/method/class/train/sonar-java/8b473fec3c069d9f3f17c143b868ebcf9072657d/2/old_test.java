  @Test
  void isParametrizedMethod() {
    JavaTree.CompilationUnitTreeImpl cu = test("class C { <T> void m(T p) { m(42); } }");
    ClassTreeImpl c = (ClassTreeImpl) cu.types().get(0);
    MethodTreeImpl method = (MethodTreeImpl) c.members().get(0);
    ExpressionStatementTreeImpl s = (ExpressionStatementTreeImpl) Objects.requireNonNull(method.block()).body().get(0);
    MethodInvocationTreeImpl methodInvocation = (MethodInvocationTreeImpl) s.expression();

    assertThat(JUtils.isParametrizedMethod(cu.sema.methodSymbol(method.methodBinding)))
      .isEqualTo(JUtils.isParametrizedMethod(method.symbol()))
      .isEqualTo(method.methodBinding.isGenericMethod())
      .isTrue();
    assertThat(JUtils.isParametrizedMethod(cu.sema.methodSymbol(methodInvocation.methodBinding)))
      .isEqualTo(JUtils.isParametrizedMethod((Symbol.MethodSymbol) methodInvocation.symbol()))
      .isEqualTo(methodInvocation.methodBinding.isParameterizedMethod())
      .isTrue();
  }