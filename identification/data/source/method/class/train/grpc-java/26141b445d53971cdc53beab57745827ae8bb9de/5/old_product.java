@Override
    public void getFeature(Point request, StreamObserver<Feature> responseObserver) {
      responseObserver.onValue(getFeature(request));
      responseObserver.onCompleted();
    }