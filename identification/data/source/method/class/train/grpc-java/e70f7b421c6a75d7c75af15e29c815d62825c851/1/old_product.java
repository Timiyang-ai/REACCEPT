@VisibleForTesting
  public static Iterable<ManagedChannelProvider> getCandidatesViaHardCoded(
      ClassLoader classLoader) {
    List<ManagedChannelProvider> list = new ArrayList<ManagedChannelProvider>();
    try {
      list.add(create(Class.forName("io.grpc.okhttp.OkHttpChannelProvider", true, classLoader)));
    } catch (ClassNotFoundException ex) {
      // ignore
    }
    try {
      list.add(create(Class.forName("io.grpc.netty.NettyChannelProvider", true, classLoader)));
    } catch (ClassNotFoundException ex) {
      // ignore
    }
    return list;
  }