public CompletableFuture<Boolean> handleFailure(Set nodes) {
        return router.sendMessageAndGetCompletable(CorfuMsgType.MANAGEMENT_FAILURE_DETECTED
                .payloadMsg(new FailureDetectorMsg(nodes)));
    }