@Override
    public Result forwardTx(Transaction tx, Node excludeNode) {
        TransactionMessage message = new TransactionMessage();
        message.setMsgBody(tx);
        return messageBusService.broadcastHashAndCache(message, excludeNode, true);
    }