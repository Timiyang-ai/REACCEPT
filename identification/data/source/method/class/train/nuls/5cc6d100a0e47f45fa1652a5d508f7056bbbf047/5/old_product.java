@Override
    public Result forwardTx(Transaction tx, Node excludeNode) {
        temporaryCacheManager.cacheTx(tx);
        ForwardTxMessage message = new ForwardTxMessage();
        message.setMsgBody(tx.getHash());
        return messageBusService.broadcast(message, excludeNode, true);
    }