@Test
    public void testProcessRequest() throws IOException, SAXException {
        WebConversation conv = new WebConversation();
        GetMethodWebRequest getRequest = new GetMethodWebRequest("http://localhost:8080/metadata-complete/TestServlet");
        WebResponse getResponse = conv.getResponse(getRequest);
        System.out.println(getResponse.getText());
    }