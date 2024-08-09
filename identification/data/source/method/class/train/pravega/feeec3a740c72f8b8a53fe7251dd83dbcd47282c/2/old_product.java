synchronized DeleteResult delete(int blockId) {
        validateBlockId(blockId, false);
        ByteBuf metadataBuf = getBlockBuffer(0);
        int deletedLength = 0;
        int successorAddress = CacheLayout.NO_ADDRESS;
        ArrayList<Integer> freedBlocks = new ArrayList<>();
        while (blockId != CacheLayout.NO_BLOCK_ID) {
            long blockMetadata = metadataBuf.getLong(blockId * this.layout.blockMetadataSize());
            if (this.layout.isUsedBlock(blockMetadata)) {
                // Clear metadata.
                freedBlocks.add(blockId);

                // Find successor, if any.
                successorAddress = this.layout.getSuccessorAddress(blockMetadata);
                deletedLength += this.layout.getLength(blockMetadata);
                if (successorAddress == CacheLayout.NO_ADDRESS || this.layout.getBufferId(successorAddress) != this.id) {
                    break;
                } else {
                    blockId = this.layout.getBlockId(successorAddress);
                    assert blockId >= 1 && blockId < this.layout.blocksPerBuffer();
                }
            } else {
                blockId = CacheLayout.NO_BLOCK_ID;
            }
        }

        deallocateBlocks(freedBlocks, metadataBuf);
        return new DeleteResult(deletedLength, successorAddress);
    }