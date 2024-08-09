  @Test
  public void getImports_Good() throws Exception {
    NestedSet<String> imports = NestedSetBuilder.create(Order.COMPILE_ORDER, "abc");
    StructImpl info =
        makeStruct(ImmutableMap.of(PyStructUtils.IMPORTS, Depset.of(SkylarkType.STRING, imports)));
    assertThat(PyStructUtils.getImports(info)).isSameInstanceAs(imports);
  }