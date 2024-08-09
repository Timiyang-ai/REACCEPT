  @Test
  public void setAccessTimeAndExpiryThenReturnMappingOutsideLock_nullExpiryForAccess() {
    strategy = OnHeapStrategy.strategy(store, ExpiryPolicy.NO_EXPIRY, timeSource);

    TestOnHeapValueHolder mapping = new TestOnHeapValueHolder(10);
    when(policy.getExpiryForAccess(1, mapping)).thenReturn(null);

    strategy.setAccessAndExpiryTimeWhenCallerOutsideLock(1, mapping, timeSource.getTimeMillis());

    assertThat(mapping.expiration).isNull();
    assertThat(mapping.now).isEqualTo(timeSource.getTimeMillis());

    verifyZeroInteractions(store);
  }