    @Test(expected = IllegalStateException.class)
    public void getMaxNumber_emptyIndex() {
        metadata.put(MAX_BLOCK_NUMBER_KEY, ByteUtil.longToBytes(9));
        target.getMaxNumber();
    }