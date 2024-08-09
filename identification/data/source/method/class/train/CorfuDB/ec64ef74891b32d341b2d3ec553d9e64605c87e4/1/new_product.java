public CompletableFuture<ClusterState> sendHeartbeatRequest() {
        return sendMessageWithFuture(CorfuMsgType.HEARTBEAT_REQUEST.msg());
    }