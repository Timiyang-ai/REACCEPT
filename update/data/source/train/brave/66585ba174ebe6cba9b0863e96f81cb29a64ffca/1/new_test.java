@Test
  public void async() throws Exception {
    get("/async");

    takeSpan();
  }