synchronized DeleteResult delete(int blockId) {
        validateBlockId(blockId, false);
        ByteBuf metadataBuf = getMetadataBlock();
        int deletedLength = 0;
        int predecessorAddress = CacheLayout.NO_ADDRESS;
        Stack<Integer> freedBlocks = new Stack<>(); // We're traversing backwards, but need these later in ascending order.
        while (blockId != CacheLayout.NO_BLOCK_ID) {
            long blockMetadata = metadataBuf.getLong(blockId * this.layout.blockMetadataSize());
            if (this.layout.isUsedBlock(blockMetadata)) {
                // Clear metadata.
                freedBlocks.push(blockId);

                // Find predecessor, if any.
                predecessorAddress = this.layout.getPredecessorAddress(blockMetadata);
                deletedLength += this.layout.getLength(blockMetadata);
                if (predecessorAddress == CacheLayout.NO_ADDRESS || this.layout.getBufferId(predecessorAddress) != this.id) {
                    break;
                } else {
                    blockId = this.layout.getBlockId(predecessorAddress);
                    assert blockId >= 1 && blockId < this.layout.blocksPerBuffer();
                }
            } else {
                blockId = CacheLayout.NO_BLOCK_ID;
            }
        }

        deallocateBlocks(freedBlocks, metadataBuf);
        return new DeleteResult(deletedLength, predecessorAddress);
    }