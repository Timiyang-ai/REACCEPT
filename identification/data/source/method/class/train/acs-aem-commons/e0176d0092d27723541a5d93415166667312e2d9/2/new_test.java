@Test
    public void testGetQueryParam_HttpServletRequest_String() {
        // MockSlingHttpServletRequest doesn't natively support getParameter
        MockSlingHttpServletRequest request = new MockSlingHttpServletRequest("/apple/macbookair", "show", "html", "simple", null) {
            public String getParameter(String name) {
                if (name.equals("ghz")) {
                    return "2.4";
                } else {
                    return null;
                }
            };
        };

        String key = "ghz";
        String expResult = "2.4";
        String result = PathInfoUtil.getQueryParam(request, key);
        assertEquals(expResult, result);
    }