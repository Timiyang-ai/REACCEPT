  @Test
  public void registerAllGrpcViews() {
    FakeViewManager fakeViewManager = new FakeViewManager();
    RpcViews.registerAllGrpcViews(fakeViewManager);
    assertThat(fakeViewManager.getRegisteredViews())
        .containsExactlyElementsIn(
            ImmutableSet.builder()
                .addAll(RpcViews.GRPC_CLIENT_VIEWS_SET)
                .addAll(RpcViews.GRPC_SERVER_VIEWS_SET)
                .build());
  }