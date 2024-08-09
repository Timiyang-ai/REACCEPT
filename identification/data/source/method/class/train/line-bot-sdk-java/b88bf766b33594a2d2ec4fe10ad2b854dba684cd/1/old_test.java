    @Test
    public void parseBytesOrNullTestForValidValue() throws Exception {
        // Do
        byte[] bytes = BeaconContentUtil.parseBytesOrNull("0123");

        // Verify
        assertThat(bytes).containsExactly(0x01, 0x23);
    }