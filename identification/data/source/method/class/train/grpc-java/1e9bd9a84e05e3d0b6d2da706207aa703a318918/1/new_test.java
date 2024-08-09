  @Test
  public void fromThrowable_shouldReturnNullIfNoEmbeddedStatus() {
    Throwable nestedSe = new Throwable(new Throwable("no status found"));

    assertNull(StatusProto.fromThrowable(nestedSe));
  }