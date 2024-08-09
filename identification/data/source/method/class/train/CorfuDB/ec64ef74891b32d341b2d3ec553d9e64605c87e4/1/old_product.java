public CompletableFuture<NodeView> sendHeartbeatRequest() {
        return sendMessageWithFuture(CorfuMsgType.HEARTBEAT_REQUEST.msg());
    }