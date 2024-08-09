@Override
    public void getFeature(Point request, StreamObserver<Feature> responseObserver) {
      responseObserver.onValue(checkFeature(request));
      responseObserver.onCompleted();
    }