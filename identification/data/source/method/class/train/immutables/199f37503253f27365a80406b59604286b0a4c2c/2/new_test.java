  @Test
  public void booleanValue() {
    BooleanHolderCriteria.booleanHolder
            .value.isTrue()
            .value.isFalse()
            .value.is(true)
            .value.is(false)
            .boxed.isTrue()
            .boxed.isFalse()
            .optional.is(true)
            .nullable.isAbsent()
            .nullable.isTrue()
            .nullable.is(true);
  }