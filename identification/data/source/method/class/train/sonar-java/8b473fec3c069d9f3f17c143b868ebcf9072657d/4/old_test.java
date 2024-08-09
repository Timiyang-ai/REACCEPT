  @Test
  void primitiveType() {
    JavaTree.CompilationUnitTreeImpl cu = test("");
    Type wrapperType = cu.sema.type(Objects.requireNonNull(cu.sema.resolveType("java.lang.Byte")));
    Type primitiveType = JUtils.primitiveType(wrapperType);

    assertThat(primitiveType).isNotNull();
    assertThat(primitiveType.fullyQualifiedName()).isEqualTo("byte");
  }