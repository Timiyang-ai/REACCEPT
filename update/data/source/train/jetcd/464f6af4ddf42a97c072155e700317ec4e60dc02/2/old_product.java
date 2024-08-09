@Override
  public CompletableFuture<MemberUpdateResponse> updateMember(
      long memberID, List<String> peerAddrs) {
    MemberUpdateRequest memberUpdateRequest = MemberUpdateRequest.newBuilder()
        .addAllPeerURLs(peerAddrs)
        .setID(memberID)
        .build();
    return Util.toCompletableFuture(
        this.stub.memberUpdate(memberUpdateRequest),
        MemberUpdateResponse::new,
        this.connectionManager.getExecutorService()
    );
  }