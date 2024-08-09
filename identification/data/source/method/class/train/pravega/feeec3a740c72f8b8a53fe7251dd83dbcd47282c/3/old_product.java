synchronized WriteResult write(InputStream data, int length, boolean first) throws IOException {
        if (this.usedBlockCount >= this.layout.blocksPerBuffer()) {
            // Full
            return null;
        }

        ArrayList<Integer> writtenBlocks = new ArrayList<>();
        int lastBlockLength = length % this.layout.blockSize();
        if (lastBlockLength == 0 && length > 0) {
            lastBlockLength = this.layout.blockSize();
        }

        ByteBuf metadataBuf = getBlockBuffer(0);
        long blockMetadata = metadataBuf.getLong(0);
        int blockId = this.layout.getNextFreeBlockId(blockMetadata);
        assert blockId != CacheLayout.NO_BLOCK_ID;
        while (blockId != CacheLayout.NO_BLOCK_ID) {
            blockMetadata = metadataBuf.getLong(blockId * this.layout.blockMetadataSize());
            assert !this.layout.isUsedBlock(blockMetadata);
            writtenBlocks.add(blockId);
            if (length > 0) {
                length -= writeBlock(getBlockBuffer(blockId), 0, data, Math.min(length, this.layout.blockSize()));
            }

            blockId = this.layout.getNextFreeBlockId(blockMetadata);
            if (length <= 0) {
                // We are done.
                break;
            }
        }

        // Update the metadata into the buffer, now that we know the successors as well.
        blockMetadata = metadataBuf.getLong(0);
        blockMetadata = this.layout.setNextFreeBlockId(blockMetadata, blockId);
        metadataBuf.setLong(0, blockMetadata);

        // Each modified metadata.
        for (int i = 0; i < writtenBlocks.size(); i++) {
            blockId = writtenBlocks.get(i);
            boolean firstBlock = first && i == 0;
            boolean last = i == writtenBlocks.size() - 1;
            int successorAddress = last ? CacheLayout.NO_ADDRESS : this.layout.calculateAddress(this.id, writtenBlocks.get(i + 1));
            int blockLength = last && length == 0 ? lastBlockLength : this.layout.blockSize();
            long metadata = this.layout.newBlockMetadata(firstBlock, CacheLayout.NO_BLOCK_ID, blockLength, successorAddress);
            metadataBuf.setLong(blockId * this.layout.blockMetadataSize(), metadata);
        }

        this.usedBlockCount += writtenBlocks.size();
        return new WriteResult(length,
                this.layout.calculateAddress(this.id, writtenBlocks.get(0)),
                this.layout.calculateAddress(this.id, writtenBlocks.get(writtenBlocks.size() - 1)));
    }