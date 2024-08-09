@Override
  public void getTopChannels(
      GetTopChannelsRequest request, StreamObserver<GetTopChannelsResponse> responseObserver) {
    InternalChannelz.RootChannelList rootChannels
        = channelz.getRootChannels(request.getStartChannelId(), maxPageSize);

    GetTopChannelsResponse resp;
    try {
      resp = ChannelzProtoUtil.toGetTopChannelResponse(rootChannels);
    } catch (StatusRuntimeException e) {
      responseObserver.onError(e);
      return;
    }

    responseObserver.onNext(resp);
    responseObserver.onCompleted();
  }