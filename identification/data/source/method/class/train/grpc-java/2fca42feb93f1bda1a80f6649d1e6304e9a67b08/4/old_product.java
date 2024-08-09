@Override
  public void getChannel(
      GetChannelRequest request, StreamObserver<GetChannelResponse> responseObserver) {
    InternalInstrumented<ChannelStats> s = channelz.getRootChannel(request.getChannelId());
    if (s == null) {
      responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
      return;
    }

    GetChannelResponse resp;
    try {
      resp = GetChannelResponse
          .newBuilder()
          .setChannel(ChannelzProtoUtil.toChannel(s))
          .build();
    } catch (StatusRuntimeException e) {
      responseObserver.onError(e);
      return;
    }

    responseObserver.onNext(resp);
    responseObserver.onCompleted();
  }