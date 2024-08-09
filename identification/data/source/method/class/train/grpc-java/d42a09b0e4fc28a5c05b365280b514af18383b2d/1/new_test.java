  @Test
  public void usePlaintext_newClientTransportAllowed() {
    OkHttpChannelBuilder builder = OkHttpChannelBuilder.forAddress("host", 1234).usePlaintext();
    builder.buildTransportFactory().newClientTransport(
        new InetSocketAddress(5678),
        new ClientTransportFactory.ClientTransportOptions(), new FakeChannelLogger());
  }