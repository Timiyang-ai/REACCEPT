  @Test
  void isIntersectionType() {
    JavaTree.CompilationUnitTreeImpl cu = test(
      "class C { java.io.Serializable f = (java.util.Comparator<Object> & java.io.Serializable) (o1, o2) -> o1.toString().compareTo(o2.toString()); }");
    ClassTreeImpl c = (ClassTreeImpl) cu.types().get(0);
    VariableTreeImpl f = (VariableTreeImpl) c.members().get(0);
    TypeCastExpressionTreeImpl e = (TypeCastExpressionTreeImpl) f.initializer();

    assertThat(JUtils.isIntersectionType(cu.sema.type(e.typeBinding)))
      .isEqualTo(JUtils.isIntersectionType(e.symbolType()))
      .isTrue();
  }