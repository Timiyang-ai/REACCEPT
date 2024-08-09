  @Test
  public void stringAttributeValue() {
    AttributeValue attribute = AttributeValue.stringAttributeValue("MyStringAttributeValue");
    attribute.match(
        new Function<String, Object>() {
          @Override
          @Nullable
          public Object apply(String stringValue) {
            assertThat(stringValue).isEqualTo("MyStringAttributeValue");
            return null;
          }
        },
        new Function<Boolean, Object>() {
          @Override
          @Nullable
          public Object apply(Boolean booleanValue) {
            fail("Expected a String");
            return null;
          }
        },
        new Function<Long, Object>() {
          @Override
          @Nullable
          public Object apply(Long longValue) {
            fail("Expected a String");
            return null;
          }
        },
        Functions.throwIllegalArgumentException());
  }