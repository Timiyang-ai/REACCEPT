public static ByteBuffer createTransactionGossipPacket(TransactionViewModel tvm, byte[] requestedHash) {
        byte[] truncatedTx = TransactionTruncator.truncateTransaction(tvm.getBytes());
        final short payloadLengthBytes = (short) (truncatedTx.length + GOSSIP_REQUESTED_TX_HASH_BYTES_LENGTH);
        ByteBuffer buf = ByteBuffer.allocate(ProtocolMessage.HEADER.getMaxLength() + payloadLengthBytes);
        addProtocolHeader(buf, ProtocolMessage.TRANSACTION_GOSSIP, payloadLengthBytes);
        buf.put(truncatedTx);
        buf.put(requestedHash, 0, GOSSIP_REQUESTED_TX_HASH_BYTES_LENGTH);
        buf.flip();
        return buf;
    }