@Test
  public void data() {
    query("stream:materialize(Q{java:java.lang.Object}new()) instance of xs:anyAtomicType", "true");
  }