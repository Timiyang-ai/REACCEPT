public CompletableFuture<Boolean> handleFailure(Set failedNodes, Set healedNodes) {
        return router.sendMessageAndGetCompletable(CorfuMsgType.MANAGEMENT_FAILURE_DETECTED
                .payloadMsg(new FailureDetectorMsg(failedNodes, healedNodes)));
    }