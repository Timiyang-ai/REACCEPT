    @Test
    public void broadcastBlock() {
        ChannelManager target = new ChannelManagerImpl(mock(RskSystemProperties.class), mock(SyncPool.class));

        Block block = mock(Block.class);
        when(block.getHash()).thenReturn(new Keccak256(new byte[32]));
        Set<NodeID> nodeIds = target.broadcastBlock(block);

        assertTrue(nodeIds.isEmpty());
    }