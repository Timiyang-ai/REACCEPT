  @Test
  public void shared() throws Exception {
    ResourcePool pool = ClusteredResourcePoolBuilder.clusteredShared("resourceId");
    assertThat(pool, is(instanceOf(SharedClusteredResourcePool.class)));
    assertThat(pool.getType(), is(ClusteredResourceType.Types.SHARED));
    assertThat(pool.isPersistent(), is(true));
    assertThat(((SharedClusteredResourcePool)pool).getSharedResourcePool(), is("resourceId"));
  }