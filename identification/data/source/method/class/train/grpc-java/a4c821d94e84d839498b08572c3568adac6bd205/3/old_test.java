  @Test
  public void routeChat_simpleResponse() throws Exception {
    RouteNote fakeResponse1 = RouteNote.newBuilder().setMessage("dummy msg1").build();
    RouteNote fakeResponse2 = RouteNote.newBuilder().setMessage("dummy msg2").build();
    final List<String> messagesDelivered = new ArrayList<>();
    final List<Point> locationsDelivered = new ArrayList<>();
    final AtomicReference<StreamObserver<RouteNote>> responseObserverRef =
        new AtomicReference<StreamObserver<RouteNote>>();
    final CountDownLatch allRequestsDelivered = new CountDownLatch(1);
    // implement the fake service
    RouteGuideImplBase routeChatImpl =
        new RouteGuideImplBase() {
          @Override
          public StreamObserver<RouteNote> routeChat(StreamObserver<RouteNote> responseObserver) {
            responseObserverRef.set(responseObserver);

            StreamObserver<RouteNote> requestObserver = new StreamObserver<RouteNote>() {
              @Override
              public void onNext(RouteNote value) {
                messagesDelivered.add(value.getMessage());
                locationsDelivered.add(value.getLocation());
              }

              @Override
              public void onError(Throwable t) {
              }

              @Override
              public void onCompleted() {
                allRequestsDelivered.countDown();
              }
            };

            return requestObserver;
          }
        };
    serviceRegistry.addService(routeChatImpl);

    // start routeChat
    CountDownLatch latch = client.routeChat();

    // request message sent and delivered for four times
    assertTrue(allRequestsDelivered.await(1, TimeUnit.SECONDS));
    assertEquals(
        Arrays.asList("First message", "Second message", "Third message", "Fourth message"),
        messagesDelivered);
    assertEquals(
        Arrays.asList(
            Point.newBuilder().setLatitude(0).setLongitude(0).build(),
            Point.newBuilder().setLatitude(0).setLongitude(1).build(),
            Point.newBuilder().setLatitude(1).setLongitude(0).build(),
            Point.newBuilder().setLatitude(1).setLongitude(1).build()
        ),
        locationsDelivered);

    // Let the server send out two simple response messages
    // and verify that the client receives them.
    // Allow some timeout for verify() if not using directExecutor
    responseObserverRef.get().onNext(fakeResponse1);
    verify(testHelper).onMessage(fakeResponse1);
    responseObserverRef.get().onNext(fakeResponse2);
    verify(testHelper).onMessage(fakeResponse2);

    // let server complete.
    responseObserverRef.get().onCompleted();

    assertTrue(latch.await(1, TimeUnit.SECONDS));
    verify(testHelper, never()).onRpcError(any(Throwable.class));
  }