@Override
    public Result forwardTx(Transaction tx, Node excludeNode) {
        ForwardTxMessage message = new ForwardTxMessage();
        message.setMsgBody(tx.getHash());
        return messageBusService.broadcast(message, excludeNode, true);
    }