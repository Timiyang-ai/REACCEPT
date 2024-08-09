  @Test
  public void decideLoadBalancerProvider_serviceConfigLbPolicy() throws Exception {
    AutoConfiguredLoadBalancer lb = lbf.newLoadBalancer(new TestHelper());
    Map<String, String> serviceConfig = new HashMap<>();
    serviceConfig.put("loadBalancingPolicy", "round_robin");
    List<EquivalentAddressGroup> servers =
        Arrays.asList(
            new EquivalentAddressGroup(
                new SocketAddress(){},
                Attributes.newBuilder().set(GrpcAttributes.ATTR_LB_ADDR_AUTHORITY, "ok").build()),
            new EquivalentAddressGroup(
                new SocketAddress(){}));
    List<EquivalentAddressGroup> backends = Arrays.asList(servers.get(1));
    PolicySelection selection = lb.decideLoadBalancerProvider(servers, serviceConfig);

    assertThat(selection.provider.getClass().getName()).isEqualTo(
        "io.grpc.util.SecretRoundRobinLoadBalancerProvider$Provider");
    assertThat(selection.serverList).isEqualTo(backends);
    assertThat(selection.config).isEqualTo(Collections.<String, Object>emptyMap());
    verifyZeroInteractions(channelLogger);
  }