  @Test
  void primitiveWrapperType() {
    JavaTree.CompilationUnitTreeImpl cu = test("");
    Type primitiveType = cu.sema.type(Objects.requireNonNull(cu.sema.resolveType("byte")));
    Type wrapperType = JUtils.primitiveWrapperType(primitiveType);

    assertThat(wrapperType).isNotNull();
    assertThat(JUtils.isPrimitiveWrapper(wrapperType)).isTrue();
    assertThat(wrapperType.fullyQualifiedName()).isEqualTo("java.lang.Byte");
  }