@Override
  public void getSubchannel(
      GetSubchannelRequest request, StreamObserver<GetSubchannelResponse> responseObserver) {
    Instrumented<ChannelStats> s = channelz.getSubchannel(request.getSubchannelId());
    if (s == null) {
      responseObserver.onError(Status.NOT_FOUND.asRuntimeException());
      return;
    }

    responseObserver.onNext(
        GetSubchannelResponse
            .newBuilder()
            .setSubchannel(ChannelzProtoUtil.toSubchannel(s))
            .build());
    responseObserver.onCompleted();
  }