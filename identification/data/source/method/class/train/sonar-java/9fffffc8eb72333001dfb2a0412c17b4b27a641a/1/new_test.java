  @Test
  void name() {
    JavaTree.CompilationUnitTreeImpl cu = test("class C { C() { } void m() { } }");
    ClassTreeImpl c = (ClassTreeImpl) cu.types().get(0);
    MethodTreeImpl constructor = (MethodTreeImpl) c.members().get(0);
    MethodTreeImpl method = (MethodTreeImpl) c.members().get(1);
    assertThat(cu.sema.typeSymbol(c.typeBinding).name())
      .isEqualTo(c.symbol().name())
      .isEqualTo("C");
    assertThat(cu.sema.methodSymbol(constructor.methodBinding).name())
      .isEqualTo(constructor.symbol().name())
      .isEqualTo("<init>");
    assertThat(cu.sema.methodSymbol(method.methodBinding).name())
      .isEqualTo(method.symbol().name())
      .isEqualTo("m");
  }