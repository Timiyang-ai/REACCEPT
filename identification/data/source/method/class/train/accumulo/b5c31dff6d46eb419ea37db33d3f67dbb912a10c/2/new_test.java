  private ServerAddress startServer() throws Exception {
    ServerContext ctx = createMock(ServerContext.class);
    expect(ctx.getZooReaderWriter()).andReturn(null).anyTimes();
    expect(ctx.getInstanceID()).andReturn("instance").anyTimes();
    expect(ctx.getConfiguration()).andReturn(factory.getSystemConfiguration()).anyTimes();
    expect(ctx.getThriftServerType()).andReturn(ThriftServerType.THREADPOOL);
    expect(ctx.getServerSslParams()).andReturn(null).anyTimes();
    expect(ctx.getSaslParams()).andReturn(null).anyTimes();
    expect(ctx.getClientTimeoutInMillis()).andReturn((long) 1000).anyTimes();
    replay(ctx);
    ClientServiceHandler clientHandler = new ClientServiceHandler(ctx, null, null);
    Iface rpcProxy = TraceUtil.wrapService(clientHandler);
    Processor<Iface> processor = new Processor<>(rpcProxy);
    // "localhost" explicitly to make sure we can always bind to that interface (avoids DNS
    // misconfiguration)
    String hostname = "localhost";

    return TServerUtils.startServer(Metrics.initSystem(getClass().getSimpleName()), ctx, hostname,
        Property.TSERV_CLIENTPORT, processor, "TServerUtilsTest", "TServerUtilsTestThread",
        Property.TSERV_PORTSEARCH, Property.TSERV_MINTHREADS, Property.TSERV_THREADCHECK,
        Property.GENERAL_MAX_MESSAGE_SIZE);

  }