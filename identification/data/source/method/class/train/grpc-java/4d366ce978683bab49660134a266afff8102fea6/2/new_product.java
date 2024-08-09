@Override
  public void getTopChannels(
      GetTopChannelsRequest request, StreamObserver<GetTopChannelsResponse> responseObserver) {
    InternalChannelz.RootChannelList rootChannels
        = channelz.getRootChannels(request.getStartChannelId(), maxPageSize);

    responseObserver.onNext(ChannelzProtoUtil.toGetTopChannelResponse(rootChannels));
    responseObserver.onCompleted();
  }