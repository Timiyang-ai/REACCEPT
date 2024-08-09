@Override
    public long getMaxNumber() {
        if (index.isEmpty()) {
            return -1;
        }

        return ByteUtil.byteArrayToLong(metadata.get(MAX_BLOCK_NUMBER_KEY));
    }