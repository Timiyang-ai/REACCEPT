  private <T extends Comparable<T>> void
  validateCondition(PartitionFilter filter, String field, boolean single, Partitioning.FieldType type, T ... values) {
    PartitionFilter.Condition<? extends Comparable> condition = filter.getCondition(field);
    Assert.assertNotNull(condition);
    Assert.assertEquals(single, condition.isSingleValue());
    type.validate(condition.getValue());
    boolean expectMatch = true;
    for (T value : values) {
      if (value == null) {
        expectMatch = false;
        continue;
      }
      Assert.assertEquals(expectMatch, condition.match(value));
    }
  }