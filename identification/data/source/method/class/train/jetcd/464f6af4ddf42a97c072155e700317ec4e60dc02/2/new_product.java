@Override
  public CompletableFuture<MemberUpdateResponse> updateMember(
      long memberID, List<URI> peerAddrs) {
    MemberUpdateRequest memberUpdateRequest = MemberUpdateRequest.newBuilder()
        .addAllPeerURLs(peerAddrs.stream().map(uri -> uri.toString()).collect(Collectors.toList()))
        .setID(memberID)
        .build();
    return Util.toCompletableFuture(
        this.stub.memberUpdate(memberUpdateRequest),
        MemberUpdateResponse::new,
        this.connectionManager.getExecutorService()
    );
  }