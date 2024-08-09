public CompletableFuture<Boolean> handleFailure(Map nodes) {
        return router.sendMessageAndGetCompletable(CorfuMsgType.MANAGEMENT_FAILURE_DETECTED.payloadMsg(new FailureDetectorMsg(nodes)));
    }