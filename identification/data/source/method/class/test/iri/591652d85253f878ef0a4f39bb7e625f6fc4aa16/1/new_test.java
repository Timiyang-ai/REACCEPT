    @Test
    public void createTransactionGossipPacket() {
        Transaction sourceTx = new Transaction();
        sourceTx.bytes = TransactionTestUtils.constructTransactionBytes();
        TransactionViewModel tvm = new TransactionViewModel(sourceTx, null);
        ByteBuffer buf = Protocol.createTransactionGossipPacket(tvm, Hash.NULL_HASH.bytes());
        final int expectedMessageSize = Transaction.SIZE - TransactionTestUtils.TRUNCATION_BYTES_COUNT
                + Protocol.GOSSIP_REQUESTED_TX_HASH_BYTES_LENGTH;
        assertEquals("buffer should have the right capacity",
                Protocol.PROTOCOL_HEADER_BYTES_LENGTH + expectedMessageSize, buf.capacity());
        assertEquals("should be of type tx gossip message", ProtocolMessage.TRANSACTION_GOSSIP.getTypeID(), buf.get());
        assertEquals("should have correct message length", expectedMessageSize, buf.getShort());
    }