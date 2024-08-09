  @Test
  void enclosingClass() {
    JavaTree.CompilationUnitTreeImpl cu = test("class C1 { int f; class C2 { } void m(int p) { class C3 { } } }");
    ClassTreeImpl c1 = (ClassTreeImpl) cu.types().get(0);
    VariableTreeImpl f = (VariableTreeImpl) c1.members().get(0);
    ClassTreeImpl c2 = (ClassTreeImpl) c1.members().get(1);
    MethodTreeImpl m = (MethodTreeImpl) c1.members().get(2);
    VariableTreeImpl p = (VariableTreeImpl) m.parameters().get(0);
    ClassTreeImpl c3 = (ClassTreeImpl) m.block().body().get(0);

    assertThat(cu.sema.typeSymbol(c1.typeBinding).enclosingClass())
      .as("of top-level class")
      .isSameAs(cu.sema.typeSymbol(c1.typeBinding));

    assertThat(cu.sema.typeSymbol(c2.typeBinding).enclosingClass())
      .as("of nested class")
      .isSameAs(cu.sema.typeSymbol(c1.typeBinding));

    assertThat(cu.sema.typeSymbol(c3.typeBinding).enclosingClass())
      .as("of local class")
      .isSameAs(cu.sema.typeSymbol(c1.typeBinding));

    assertThat(cu.sema.methodSymbol(m.methodBinding).enclosingClass())
      .as("of method")
      .isSameAs(cu.sema.typeSymbol(c1.typeBinding));

    assertThat(cu.sema.variableSymbol(f.variableBinding).enclosingClass())
      .as("of field")
      .isSameAs(cu.sema.typeSymbol(c1.typeBinding));

    assertThat(cu.sema.variableSymbol(p.variableBinding).enclosingClass())
      .as("of method parameter")
      .isSameAs(cu.sema.typeSymbol(c1.typeBinding));
  }