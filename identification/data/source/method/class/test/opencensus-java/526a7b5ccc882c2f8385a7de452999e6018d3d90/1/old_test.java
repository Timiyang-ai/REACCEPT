  @Test
  public void equals_IgnoresTagOrderAndTagContextClass() {
    new EqualsTester()
        .addEqualityGroup(
            new SimpleTagContext(TAG1, TAG2),
            new SimpleTagContext(TAG1, TAG2),
            new SimpleTagContext(TAG2, TAG1),
            new TagContext() {
              @Override
              protected Iterator<Tag> getIterator() {
                return Lists.newArrayList(TAG1, TAG2).iterator();
              }
            })
        .testEquals();
  }