@Test
    public void testGetQueryParam_HttpServletRequest_String() {
        MockSlingHttpServletRequest request = new MockSlingHttpServletRequest("/apple/macbookair", "show", "html", "simple", "ghz=2.4");

        String key = "ghz";
        String expResult = "2.4";
        String result = PathInfoUtil.getQueryParam(request, key);
        //assertEquals(expResult, result);
    }