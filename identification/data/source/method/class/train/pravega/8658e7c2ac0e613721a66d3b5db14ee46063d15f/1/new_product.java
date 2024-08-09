synchronized WriteResult write(BufferView data, int predecessorAddress) {
        if (this.usedBlockCount >= this.layout.blocksPerBuffer()) {
            // Full
            return null;
        }

        ByteBuf metadataBuf = getMetadataBlock();
        long blockMetadata = metadataBuf.getLong(0);
        int blockId = this.layout.getNextFreeBlockId(blockMetadata);
        assert blockId != CacheLayout.NO_BLOCK_ID;
        final int firstBlockId = blockId;
        int dataOffset = 0;
        try {
            // Use a do-while loop since we want to properly handle the (valid) case when data.getLength() == 0.
            do {
                int bufIndex = blockId * this.layout.blockMetadataSize();
                blockMetadata = metadataBuf.getLong(bufIndex);
                assert !this.layout.isUsedBlock(blockMetadata);

                // Copy the data to the block.
                int blockLength = Math.min(data.getLength() - dataOffset, this.layout.blockSize());
                if (blockLength > 0) {
                    data.slice(dataOffset, blockLength).copyTo(getWriteableBlock(blockId, 0));
                    dataOffset += blockLength;
                }

                // Update block metadata.
                long metadata = this.layout.newBlockMetadata(CacheLayout.NO_BLOCK_ID, blockLength, predecessorAddress);
                metadataBuf.setLong(bufIndex, metadata);
                this.usedBlockCount++;

                // Move on to the next block to write.
                predecessorAddress = this.layout.calculateAddress(this.id, blockId);
                blockId = this.layout.getNextFreeBlockId(blockMetadata);
            } while (blockId != CacheLayout.NO_BLOCK_ID && dataOffset < data.getLength());
        } catch (Throwable ex) {
            if (!Exceptions.mustRethrow(ex)) {
                // We wrote something, but got interrupted. We need to clean up whatever we wrote so we don't leave
                // unreferenced data in the buffer.
                rollbackWrite(this.layout.getBlockId(predecessorAddress), blockId);
            }

            throw ex;
        }

        // Update the root metadata.
        blockMetadata = metadataBuf.getLong(0);
        blockMetadata = this.layout.setNextFreeBlockId(blockMetadata, blockId);
        metadataBuf.setLong(0, blockMetadata);

        return new WriteResult(dataOffset, predecessorAddress, firstBlockId);
    }