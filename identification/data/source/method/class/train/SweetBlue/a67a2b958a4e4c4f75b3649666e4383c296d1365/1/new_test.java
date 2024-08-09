    @Test
    public void subDataTest() throws Exception
    {
        startTest(false);
        final byte[] empty = new byte[0];

        ByteBuffer buff = new ByteBuffer(2);

        buff.append(array1);
        buff.append(array2);

        byte[] sub = buff.subData(2, 5);

        assertArrayEquals(sub, new byte[] { 0x03, 0x04, 0x05, 0x06, 0x07 });

        assertArrayEquals(buff.subData(11, 20), empty);

        byte[] sub2 = buff.subData(8, 5);

        assertArrayEquals(sub2, new byte[] { 0x09, 0x0A });

        buff.setToSubData(2, 5);

        assertArrayEquals(sub, buff.bytes());

        buff.setToSubData(11, 10);

        assertArrayEquals(sub, buff.bytes());
        succeed();
    }