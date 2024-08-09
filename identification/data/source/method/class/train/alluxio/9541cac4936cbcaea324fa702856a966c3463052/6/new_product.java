public static int getConf(ClientContext ctx, String... args) {
    return getConfImpl(
        () -> new RetryHandlingMetaMasterConfigClient(MasterClientContext.newBuilder(ctx).build()),
        ctx.getClusterConf(), args);
  }