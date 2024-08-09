@Override
  public void getServers(
      GetServersRequest request, StreamObserver<GetServersResponse> responseObserver) {
    ServerList servers = channelz.getServers(request.getStartServerId(), maxPageSize);

    GetServersResponse resp;
    try {
      resp = ChannelzProtoUtil.toGetServersResponse(servers);
    } catch (StatusRuntimeException e) {
      responseObserver.onError(e);
      return;
    }

    responseObserver.onNext(resp);
    responseObserver.onCompleted();
  }