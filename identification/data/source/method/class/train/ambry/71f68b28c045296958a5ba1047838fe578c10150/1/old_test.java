  @Test
  public void getHeaderTest() throws RestServiceException {
    Map<String, Object> args = new HashMap<>();
    args.put("HeaderA", "ValueA");
    args.put("HeaderB", null);

    // get HeaderA
    assertEquals("Header value does not match", args.get("HeaderA"), RestUtils.getHeader(args, "HeaderA", true));
    assertEquals("Header value does not match", args.get("HeaderA"), RestUtils.getHeader(args, "HeaderA", false));
    // get HeaderB
    assertNull("There should be no value for HeaderB", RestUtils.getHeader(args, "HeaderB", false));
    try {
      RestUtils.getHeader(args, "HeaderB", true);
      fail("Getting HeaderB as required should have failed");
    } catch (RestServiceException e) {
      assertEquals("Unexpected RestServiceErrorCode", RestServiceErrorCode.InvalidArgs, e.getErrorCode());
    }

    // get HeaderC
    assertNull("There should be no value for HeaderC", RestUtils.getHeader(args, "HeaderB", false));
    try {
      RestUtils.getHeader(args, "Headerc", true);
      fail("Getting HeaderB as required should have failed");
    } catch (RestServiceException e) {
      assertEquals("Unexpected RestServiceErrorCode", RestServiceErrorCode.MissingArgs, e.getErrorCode());
    }
  }