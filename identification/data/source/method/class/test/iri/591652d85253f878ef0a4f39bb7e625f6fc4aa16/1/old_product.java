public static ByteBuffer createTransactionGossipPacket(TransactionViewModel tvm, byte[] requestedHash) {
        byte[] truncatedTx = truncateTx(tvm.getBytes());
        final short payloadLengthBytes = (short) (truncatedTx.length + GOSSIP_REQUESTED_TX_HASH_BYTES);
        ByteBuffer buf = ByteBuffer.allocate(ProtocolMessage.HEADER.getMaxLength() + payloadLengthBytes);
        addProtocolHeader(buf, ProtocolMessage.TRANSACTION_GOSSIP, payloadLengthBytes);
        buf.put(truncatedTx);
        buf.put(requestedHash, 0, GOSSIP_REQUESTED_TX_HASH_BYTES);
        buf.flip();
        return buf;
    }