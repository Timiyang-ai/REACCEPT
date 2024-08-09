@Test
  public void closeTest()
      throws IOException, RestServiceException {
    NettyRequest nettyRequest = createNettyRequest(HttpMethod.POST, "/", null);
    Queue<HttpContent> httpContents = new LinkedBlockingQueue<HttpContent>();
    for (int i = 0; i < 5; i++) {
      ByteBuffer content = ByteBuffer.wrap(getRandomBytes(1024));
      HttpContent httpContent = new DefaultHttpContent(Unpooled.wrappedBuffer(content));
      nettyRequest.addContent(httpContent);
      httpContents.add(httpContent);
    }
    closeRequestAndValidate(nettyRequest);
    while (httpContents.peek() != null) {
      assertEquals("Reference count of http content has changed", 1, httpContents.poll().refCnt());
    }
  }