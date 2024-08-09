  @Test
  public void booleanAttributeValue() {
    AttributeValue attribute = AttributeValue.booleanAttributeValue(true);
    attribute.match(
        new Function<String, Object>() {
          @Override
          @Nullable
          public Object apply(String stringValue) {
            fail("Expected a Boolean");
            return null;
          }
        },
        new Function<Boolean, Object>() {
          @Override
          @Nullable
          public Object apply(Boolean booleanValue) {
            assertThat(booleanValue).isTrue();
            return null;
          }
        },
        new Function<Long, Object>() {
          @Override
          @Nullable
          public Object apply(Long longValue) {
            fail("Expected a Boolean");
            return null;
          }
        },
        Functions.throwIllegalArgumentException());
  }