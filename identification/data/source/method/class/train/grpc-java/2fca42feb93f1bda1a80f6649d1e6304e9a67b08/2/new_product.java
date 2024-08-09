@Override
  public void getSubchannel(
      GetSubchannelRequest request, StreamObserver<GetSubchannelResponse> responseObserver) {
    InternalInstrumented<ChannelStats> s = channelz.getSubchannel(request.getSubchannelId());
    if (s == null) {
      responseObserver.onError(
          Status.NOT_FOUND.withDescription("Can't find subchannel " + request.getSubchannelId())
              .asRuntimeException());
      return;
    }

    GetSubchannelResponse resp;
    try {
      resp = GetSubchannelResponse
          .newBuilder()
          .setSubchannel(ChannelzProtoUtil.toSubchannel(s))
          .build();
    } catch (StatusRuntimeException e) {
      responseObserver.onError(e);
      return;
    }

    responseObserver.onNext(resp);
    responseObserver.onCompleted();
  }