@Override
  public void getChannel(
      GetChannelRequest request, StreamObserver<GetChannelResponse> responseObserver) {
    Instrumented<ChannelStats> s = channelz.getRootChannel(request.getChannelId());
    if (s == null) {
      responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
      return;
    }

    responseObserver.onNext(
        GetChannelResponse
            .newBuilder()
            .setChannel(ChannelzProtoUtil.toChannel(s))
            .build());
    responseObserver.onCompleted();
  }