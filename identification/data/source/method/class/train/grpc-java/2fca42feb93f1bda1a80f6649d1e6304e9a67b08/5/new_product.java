@Override
  public void getSocket(
      GetSocketRequest request, StreamObserver<GetSocketResponse> responseObserver) {
    InternalInstrumented<SocketStats> s = channelz.getSocket(request.getSocketId());
    if (s == null) {
      responseObserver.onError(
          Status.NOT_FOUND.withDescription("Can't find socket " + request.getSocketId())
              .asRuntimeException());
      return;
    }

    GetSocketResponse resp;
    try {
      resp =
          GetSocketResponse.newBuilder().setSocket(ChannelzProtoUtil.toSocket(s)).build();
    } catch (StatusRuntimeException e) {
      responseObserver.onError(e);
      return;
    }

    responseObserver.onNext(resp);
    responseObserver.onCompleted();
  }