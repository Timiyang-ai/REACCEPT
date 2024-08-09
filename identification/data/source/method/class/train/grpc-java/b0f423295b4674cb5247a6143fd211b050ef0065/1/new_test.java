  @Test
  public void getCandidatesViaHardCoded_multipleProvider() throws Exception {
    Iterator<ServiceProvidersTestAbstractProvider> candidates =
        ServiceProviders.getCandidatesViaHardCoded(
            ServiceProvidersTestAbstractProvider.class,
            ImmutableList.<Class<?>>of(
                Available7Provider.class,
                Available0Provider.class))
            .iterator();
    assertEquals(Available7Provider.class, candidates.next().getClass());
    assertEquals(Available0Provider.class, candidates.next().getClass());
    assertFalse(candidates.hasNext());
  }