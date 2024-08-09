  @Test(expected = NullPointerException.class)
  public void fromDescriptionAndAttributes_NullDescription() {
    Annotation.fromDescriptionAndAttributes(null, Collections.<String, AttributeValue>emptyMap());
  }