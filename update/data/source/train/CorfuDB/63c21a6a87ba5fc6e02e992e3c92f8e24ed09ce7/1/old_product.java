public CompletableFuture<Boolean> restart() {
        return router.sendMessageAndGetCompletable(new CorfuMsg(CorfuMsgType.RESTART));
    }