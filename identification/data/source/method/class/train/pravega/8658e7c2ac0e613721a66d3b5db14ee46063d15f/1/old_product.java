synchronized WriteResult write(InputStream data, int remainingLength, int predecessorAddress) throws IOException {
        if (this.usedBlockCount >= this.layout.blocksPerBuffer()) {
            // Full
            return null;
        }

        int lastBlockLength = remainingLength % this.layout.blockSize();
        if (lastBlockLength == 0 && remainingLength > 0) {
            lastBlockLength = this.layout.blockSize();
        }

        ByteBuf metadataBuf = getBlockBuffer(0);
        long blockMetadata = metadataBuf.getLong(0);
        int blockId = this.layout.getNextFreeBlockId(blockMetadata);
        assert blockId != CacheLayout.NO_BLOCK_ID;
        final int firstBlockId = blockId;
        try {
            while (blockId != CacheLayout.NO_BLOCK_ID) {
                int bufIndex = blockId * this.layout.blockMetadataSize();
                blockMetadata = metadataBuf.getLong(bufIndex);
                assert !this.layout.isUsedBlock(blockMetadata);
                int blockLength = Math.min(remainingLength, this.layout.blockSize());
                if (blockLength > 0) {
                    blockLength = writeBlock(getBlockBuffer(blockId), 0, data, blockLength);
                }

                // Update block metadata.
                long metadata = this.layout.newBlockMetadata(CacheLayout.NO_BLOCK_ID, blockLength, predecessorAddress);
                metadataBuf.setLong(bufIndex, metadata);
                this.usedBlockCount++;

                // Move on to the next block to write.
                predecessorAddress = this.layout.calculateAddress(this.id, blockId);
                blockId = this.layout.getNextFreeBlockId(blockMetadata);
                remainingLength -= blockLength;

                if (remainingLength <= 0) {
                    // We are done.
                    break;
                }
            }
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

        return new WriteResult(remainingLength, predecessorAddress, firstBlockId);
    }