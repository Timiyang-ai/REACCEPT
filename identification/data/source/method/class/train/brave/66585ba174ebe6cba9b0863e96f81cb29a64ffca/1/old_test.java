@Test
  public void async() throws Exception {
    get("/async");

    assertThat(spans).hasSize(1);
  }