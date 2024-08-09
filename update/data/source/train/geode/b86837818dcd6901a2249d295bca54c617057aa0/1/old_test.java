@Test
  public void testInvalidate1() throws Exception {
    doFilter();

    HttpSession session = ((HttpServletRequest) getFilteredRequest()).getSession();
    session.invalidate();

    try {
      session.getAttribute("foo");
      fail("Session should be invalid and an exception should be thrown");
    } catch (IllegalStateException iex) {
      // Pass
    }
  }