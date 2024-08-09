  @Test
  public void copyOf_nullArgument() {
    try {
      copyOf((Graph<?>) null);
      fail("Should have rejected a null graph.");
    } catch (NullPointerException expected) {
    }
  }