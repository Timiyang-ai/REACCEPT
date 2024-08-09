  @Test
  public void invoke() throws IOException, ServletException {
    Assertions.assertNotNull(new Expectations() {
      {
        valve.getNext();
        result = valveBase;
      }
    });
    valve.invoke(request, response);
  }