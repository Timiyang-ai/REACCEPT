    public void testbuildPageLink() throws Exception {
        RhnMockHttpServletRequest request = new RhnMockHttpServletRequest();
        request.setupAddParameter("someparam", "value");
        request.setupQueryString("otherparam=foo&barparam=beer");
        request.addAttribute("requestedUri", "http://localhost/rhn/somePage.do");

        RequestContext requestContext = new RequestContext(request);

        String url = requestContext.buildPageLink("someparam", "value");
        assertEquals("http://localhost/rhn/somePage.do?" +
                "someparam=value&otherparam=foo&barparam=beer", url);
        request.setupQueryString("otherparam=foo&barparam=beer&someparam=value");
        url = requestContext.buildPageLink("someparam", "zzzzz");

        assertEquals("http://localhost/rhn/somePage.do?" +
                "barparam=beer&otherparam=foo&someparam=zzzzz", url);
    }