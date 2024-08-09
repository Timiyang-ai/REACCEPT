  @Test
  public void routeChat() {
    Point p1 = Point.newBuilder().setLongitude(1).setLatitude(1).build();
    Point p2 = Point.newBuilder().setLongitude(2).setLatitude(2).build();
    RouteNote n1 = RouteNote.newBuilder().setLocation(p1).setMessage("m1").build();
    RouteNote n2 = RouteNote.newBuilder().setLocation(p2).setMessage("m2").build();
    RouteNote n3 = RouteNote.newBuilder().setLocation(p1).setMessage("m3").build();
    RouteNote n4 = RouteNote.newBuilder().setLocation(p2).setMessage("m4").build();
    RouteNote n5 = RouteNote.newBuilder().setLocation(p1).setMessage("m5").build();
    RouteNote n6 = RouteNote.newBuilder().setLocation(p1).setMessage("m6").build();
    int timesOnNext = 0;

    @SuppressWarnings("unchecked")
    StreamObserver<RouteNote> responseObserver =
        (StreamObserver<RouteNote>) mock(StreamObserver.class);
    RouteGuideGrpc.RouteGuideStub stub = RouteGuideGrpc.newStub(inProcessChannel);

    StreamObserver<RouteNote> requestObserver = stub.routeChat(responseObserver);
    verify(responseObserver, never()).onNext(any(RouteNote.class));

    requestObserver.onNext(n1);
    verify(responseObserver, never()).onNext(any(RouteNote.class));

    requestObserver.onNext(n2);
    verify(responseObserver, never()).onNext(any(RouteNote.class));

    requestObserver.onNext(n3);
    ArgumentCaptor<RouteNote> routeNoteCaptor = ArgumentCaptor.forClass(RouteNote.class);
    verify(responseObserver, timeout(100).times(++timesOnNext)).onNext(routeNoteCaptor.capture());
    RouteNote result = routeNoteCaptor.getValue();
    assertEquals(p1, result.getLocation());
    assertEquals("m1", result.getMessage());

    requestObserver.onNext(n4);
    routeNoteCaptor = ArgumentCaptor.forClass(RouteNote.class);
    verify(responseObserver, timeout(100).times(++timesOnNext)).onNext(routeNoteCaptor.capture());
    result = routeNoteCaptor.getAllValues().get(timesOnNext - 1);
    assertEquals(p2, result.getLocation());
    assertEquals("m2", result.getMessage());

    requestObserver.onNext(n5);
    routeNoteCaptor = ArgumentCaptor.forClass(RouteNote.class);
    timesOnNext += 2;
    verify(responseObserver, timeout(100).times(timesOnNext)).onNext(routeNoteCaptor.capture());
    result = routeNoteCaptor.getAllValues().get(timesOnNext - 2);
    assertEquals(p1, result.getLocation());
    assertEquals("m1", result.getMessage());
    result = routeNoteCaptor.getAllValues().get(timesOnNext - 1);
    assertEquals(p1, result.getLocation());
    assertEquals("m3", result.getMessage());

    requestObserver.onNext(n6);
    routeNoteCaptor = ArgumentCaptor.forClass(RouteNote.class);
    timesOnNext += 3;
    verify(responseObserver, timeout(100).times(timesOnNext)).onNext(routeNoteCaptor.capture());
    result = routeNoteCaptor.getAllValues().get(timesOnNext - 3);
    assertEquals(p1, result.getLocation());
    assertEquals("m1", result.getMessage());
    result = routeNoteCaptor.getAllValues().get(timesOnNext - 2);
    assertEquals(p1, result.getLocation());
    assertEquals("m3", result.getMessage());
    result = routeNoteCaptor.getAllValues().get(timesOnNext - 1);
    assertEquals(p1, result.getLocation());
    assertEquals("m5", result.getMessage());

    requestObserver.onCompleted();
    verify(responseObserver, timeout(100)).onCompleted();
    verify(responseObserver, never()).onError(any(Throwable.class));
  }