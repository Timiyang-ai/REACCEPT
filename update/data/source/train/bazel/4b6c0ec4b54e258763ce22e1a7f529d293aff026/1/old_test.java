@Test
  public void testGetCoreInterfaceRewritingTarget_renamed() throws Exception {
    CoreLibrarySupport support =
        new CoreLibrarySupport(
            new CoreLibraryRewriter(""),
            Thread.currentThread().getContextClassLoader(),
            ImmutableList.of("java/util/"),
            ImmutableList.of(),
            ImmutableList.of());

    // regular invocations of default methods: ignored
    assertThat(
            support.getCoreInterfaceRewritingTarget(
                Opcodes.INVOKEINTERFACE,
                "java/util/Collection",
                "removeIf",
                "(Ljava/util/function/Predicate;)Z",
                true))
        .isNull();
    assertThat(
            support.getCoreInterfaceRewritingTarget(
                Opcodes.INVOKEVIRTUAL,
                "java/util/ArrayList",
                "removeIf",
                "(Ljava/util/function/Predicate;)Z",
                false))
        .isNull();

    // abstract methods: ignored
    assertThat(
            support.getCoreInterfaceRewritingTarget(
                Opcodes.INVOKEINTERFACE,
                "java/util/Collection",
                "size",
                "()I",
                true))
        .isNull();

    // static interface method
    assertThat(
            support.getCoreInterfaceRewritingTarget(
                Opcodes.INVOKESTATIC,
                "java/util/Comparator",
                "reverseOrder",
                "()Ljava/util/Comparator;",
                true))
        .isEqualTo(Comparator.class);

    // invokespecial for default methods: find nearest definition
    assertThat(
            support.getCoreInterfaceRewritingTarget(
                Opcodes.INVOKESPECIAL,
                "java/util/List",
                "removeIf",
                "(Ljava/util/function/Predicate;)Z",
                true))
        .isEqualTo(Collection.class);
    // invokespecial on a class: ignore (even if there's an inherited default method)
    assertThat(
            support.getCoreInterfaceRewritingTarget(
                Opcodes.INVOKESPECIAL,
                "java/util/ArrayList",
                "removeIf",
                "(Ljava/util/function/Predicate;)Z",
                false))
        .isNull();
  }