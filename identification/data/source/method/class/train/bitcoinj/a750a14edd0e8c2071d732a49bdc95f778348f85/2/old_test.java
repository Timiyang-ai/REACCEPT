    @Test(expected = IllegalArgumentException.class)
    public void fromPrivate_exceedsSize() {
        final byte[] bytes = new byte[33];
        bytes[0] = 42;
        ECKey.fromPrivate(bytes);
    }