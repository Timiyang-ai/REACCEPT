@Test(expected = ExchangeException.class)
  public void getCachedAccountInfo() {

    if (cachedAccountInfo == null) {
      throw new ExchangeException("getBalances method has not been called yet, or balance data has not been recieved!");
    }

  }