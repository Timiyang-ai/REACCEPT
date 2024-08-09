  @Test
  public void getCandidatesViaHardCoded_triesToLoadClasses() throws Exception {
    ServiceProvidersTestUtil.testHardcodedClasses(
        HardcodedClassesCallable.class.getName(),
        getClass().getClassLoader(),
        ImmutableSet.of(
            "io.grpc.okhttp.OkHttpChannelProvider",
            "io.grpc.netty.NettyChannelProvider"));
  }