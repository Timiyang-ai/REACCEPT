  @Test
  public void removeFailure() throws Exception {
    strategy.removeFailure(1, accessException);

    verify(store).obliterate(1);
    verify(loaderWriter).delete(1);
  }