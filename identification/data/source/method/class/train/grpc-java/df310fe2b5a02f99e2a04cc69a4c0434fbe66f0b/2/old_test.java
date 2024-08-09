  @Test
  public void greet_messageDeliveredToServer() {
    ArgumentCaptor<HelloRequest> requestCaptor = ArgumentCaptor.forClass(HelloRequest.class);

    client.greet("test name");

    verify(serviceImpl)
        .sayHello(requestCaptor.capture(), ArgumentMatchers.<StreamObserver<HelloReply>>any());
    assertEquals("test name", requestCaptor.getValue().getName());
  }