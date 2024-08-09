  @Test
  public void nearest() throws Exception {
    TieredIdentity id1 = TieredIdentityFactory.fromString("node=A,rack=rack1", mConfiguration);
    TieredIdentity id2 = TieredIdentityFactory.fromString("node=B,rack=rack2", mConfiguration);
    TieredIdentity id3 = TieredIdentityFactory.fromString("node=C,rack=rack2", mConfiguration);
    List<TieredIdentity> identities = Arrays.asList(id1, id2, id3);

    assertSame(id1, TieredIdentityUtils
        .nearest(TieredIdentityFactory.fromString("node=D,rack=rack1", mConfiguration), identities,
            mConfiguration).get());
    assertSame(id2, TieredIdentityUtils
        .nearest(TieredIdentityFactory.fromString("node=B,rack=rack2", mConfiguration), identities,
            mConfiguration).get());
    assertSame(id3, TieredIdentityUtils
        .nearest(TieredIdentityFactory.fromString("node=C,rack=rack2", mConfiguration), identities,
            mConfiguration).get());
    assertSame(id1, TieredIdentityUtils
        .nearest(TieredIdentityFactory.fromString("node=D,rack=rack3", mConfiguration), identities,
            mConfiguration).get());
  }