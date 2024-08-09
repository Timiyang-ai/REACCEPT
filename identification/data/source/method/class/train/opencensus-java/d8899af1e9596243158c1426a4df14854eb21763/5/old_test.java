  @Test
  public void longAttributeValue() {
    AttributeValue attribute = AttributeValue.longAttributeValue(123456L);
    attribute.match(
        new Function<String, Object>() {
          @Override
          @Nullable
          public Object apply(String stringValue) {
            fail("Expected a Long");
            return null;
          }
        },
        new Function<Boolean, Object>() {
          @Override
          @Nullable
          public Object apply(Boolean booleanValue) {
            fail("Expected a Long");
            return null;
          }
        },
        new Function<Long, Object>() {
          @Override
          @Nullable
          public Object apply(Long longValue) {
            assertThat(longValue).isEqualTo(123456L);
            return null;
          }
        },
        Functions.throwIllegalArgumentException());
  }