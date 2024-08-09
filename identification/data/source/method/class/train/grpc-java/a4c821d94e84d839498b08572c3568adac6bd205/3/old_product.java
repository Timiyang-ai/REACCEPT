public void routeChat() throws Exception {
    info("*** RoutChat");
    final SettableFuture<Void> finishFuture = SettableFuture.create();
    StreamObserver<RouteNote> requestObserver =
        asyncStub.routeChat(new StreamObserver<RouteNote>() {
          @Override
          public void onValue(RouteNote note) {
            info("Got message \"{0}\" at {1}, {2}", note.getMessage(), note.getLocation()
                .getLatitude(), note.getLocation().getLongitude());
          }

          @Override
          public void onError(Throwable t) {
            finishFuture.setException(t);
          }

          @Override
          public void onCompleted() {
            finishFuture.set(null);
          }
        });

    try {
      RouteNote[] requests =
          {newNote("First message", 0, 0), newNote("Second message", 0, 1),
              newNote("Third message", 1, 0), newNote("Fourth message", 1, 1)};

      for (RouteNote request : requests) {
        info("Sending message \"{0}\" at {1}, {2}", request.getMessage(), request.getLocation()
            .getLatitude(), request.getLocation().getLongitude());
        requestObserver.onValue(request);
      }
      requestObserver.onCompleted();

      finishFuture.get();
      info("Finished RouteChat");
    } catch (Exception t) {
      requestObserver.onError(t);
      logger.log(Level.WARNING, "RouteChat Failed", t);
      throw t;
    }
  }