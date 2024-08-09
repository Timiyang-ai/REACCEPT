public CompletableFuture<Boolean> reset() {
        return router.sendMessageAndGetCompletable(new CorfuMsg(CorfuMsgType.RESET));
    }