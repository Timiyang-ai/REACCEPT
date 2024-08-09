  @Test
  public void handleResolvedAddressGroups_keepOldBalancer() {
    final List<EquivalentAddressGroup> servers =
        Collections.singletonList(new EquivalentAddressGroup(new SocketAddress(){}));
    Helper helper = new TestHelper() {
      @Override
      public Subchannel createSubchannel(CreateSubchannelArgs args) {
        assertThat(args.getAddresses()).isEqualTo(servers);
        return new TestSubchannel(args);
      }
    };
    AutoConfiguredLoadBalancer lb = lbf.newLoadBalancer(helper);
    LoadBalancer oldDelegate = lb.getDelegate();

    Status handleResult = lb.tryHandleResolvedAddresses(
        ResolvedAddresses.newBuilder()
            .setAddresses(servers)
            .setAttributes(Attributes.EMPTY)
            .build());

    assertThat(handleResult.getCode()).isEqualTo(Status.Code.OK);
    assertThat(lb.getDelegate()).isSameInstanceAs(oldDelegate);
  }