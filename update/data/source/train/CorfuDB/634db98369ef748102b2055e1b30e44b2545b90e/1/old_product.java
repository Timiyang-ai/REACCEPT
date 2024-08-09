public CompletableFuture<byte[]> sendHeartbeatRequest() {
        return sendMessageWithFuture(CorfuMsgType.HEARTBEAT_REQUEST.msg());
    }