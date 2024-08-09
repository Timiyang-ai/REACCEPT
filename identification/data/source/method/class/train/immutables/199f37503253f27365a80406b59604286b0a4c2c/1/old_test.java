  @Test
  public void bigInteger() {
    BigIntegerHolderCriteria.bigIntegerHolder
            .value.atLeast(BigInteger.ONE)
            .value.is(BigInteger.ONE)
            .optional.is(BigInteger.ONE)
            .optional.atLeast(BigInteger.ONE)
            .nullable.is(BigInteger.ONE)
            .list.contains(BigInteger.ONE)
            .array.contains(BigInteger.ONE)
            .list.notEmpty()
            .array.isEmpty();
  }