  @Test
  void typeArguments() {
    JavaTree.CompilationUnitTreeImpl cu = test("class C { void m() { new java.util.HashMap<Integer, String>(); } }");
    ClassTreeImpl c = (ClassTreeImpl) cu.types().get(0);
    MethodTreeImpl m = (MethodTreeImpl) c.members().get(0);
    ExpressionStatementTreeImpl s = (ExpressionStatementTreeImpl) Objects.requireNonNull(m.block()).body().get(0);
    AbstractTypedTree e = Objects.requireNonNull((AbstractTypedTree) s.expression());

    assertThat(JUtils.typeArguments(cu.sema.type(e.typeBinding)).toString())
      .isEqualTo(JUtils.typeArguments(e.symbolType()).toString())
      .isEqualTo("[Integer, String]");
  }