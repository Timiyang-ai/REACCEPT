  @Test
  public void getUsesSharedLibraries_Good() throws Exception {
    StructImpl info = makeStruct(ImmutableMap.of(PyStructUtils.USES_SHARED_LIBRARIES, true));
    assertThat(PyStructUtils.getUsesSharedLibraries(info)).isTrue();
  }