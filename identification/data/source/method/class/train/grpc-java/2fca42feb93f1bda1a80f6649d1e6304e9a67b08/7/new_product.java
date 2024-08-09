@Override
  public void getSocket(
      GetSocketRequest request, StreamObserver<GetSocketResponse> responseObserver) {
    InternalInstrumented<SocketStats> s = channelz.getSocket(request.getSocketId());
    if (s == null) {
      responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
      return;
    }

    responseObserver.onNext(
          GetSocketResponse
              .newBuilder()
              .setSocket(ChannelzProtoUtil.toSocket(s))
              .build());
    responseObserver.onCompleted();
  }