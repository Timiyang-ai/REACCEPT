@Test
    public void testProcessRequest() throws IOException, SAXException {
        WebConversation conv = new WebConversation();
        GetMethodWebRequest getRequest = new GetMethodWebRequest(base + "/TestServlet");
        WebResponse getResponse = conv.getResponse(getRequest);
        assertTrue(getResponse.getText().contains("<title>Servlet url-pattern in web.xml</title>"));
    }