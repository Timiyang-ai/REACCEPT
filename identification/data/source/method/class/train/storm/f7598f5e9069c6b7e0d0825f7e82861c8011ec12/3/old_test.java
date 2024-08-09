    @Override
    protected boolean isTombstoneValue(byte[] value) {
        return Arrays.equals(value, encoder.getTombstoneValue());
    }