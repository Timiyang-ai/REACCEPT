@VisibleForTesting
  public static Iterable<ManagedChannelProvider> getCandidatesViaHardCoded() {
    // Class.forName(String) is used to remove the need for ProGuard configuration. Note that
    // ProGuard does not detect usages of Class.forName(String, boolean, ClassLoader):
    // https://sourceforge.net/p/proguard/bugs/418/
    List<ManagedChannelProvider> list = new ArrayList<ManagedChannelProvider>();
    try {
      list.add(create(Class.forName("io.grpc.okhttp.OkHttpChannelProvider")));
    } catch (ClassNotFoundException ex) {
      // ignore
    }
    try {
      list.add(create(Class.forName("io.grpc.netty.NettyChannelProvider")));
    } catch (ClassNotFoundException ex) {
      // ignore
    }
    return list;
  }