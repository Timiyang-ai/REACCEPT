@Override
  public void getServers(
      GetServersRequest request, StreamObserver<GetServersResponse> responseObserver) {
    ServerList servers = channelz.getServers(request.getStartServerId(), maxPageSize);

    responseObserver.onNext(ChannelzProtoUtil.toGetServersResponse(servers));
    responseObserver.onCompleted();
  }