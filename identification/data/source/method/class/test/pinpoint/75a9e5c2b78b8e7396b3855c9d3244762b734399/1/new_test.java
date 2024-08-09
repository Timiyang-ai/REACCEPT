@Test
    public void testPutVInt() throws Exception {
        checkVInt(Integer.MAX_VALUE, 5);
        checkVInt(25, 1);
        checkVInt(100, 1);

        checkVInt(Integer.MIN_VALUE, -1);

        checkVInt(Integer.MAX_VALUE / 2, -1);
        checkVInt(Integer.MAX_VALUE / 10, -1);
        checkVInt(Integer.MAX_VALUE / 10000, -1);

        checkVInt(Integer.MIN_VALUE / 2, -1);
        checkVInt(Integer.MIN_VALUE / 10, -1);
        checkVInt(Integer.MIN_VALUE / 10000, -1);


        checkVInt(0, -1);
        checkVInt(127, -1);
        checkVInt(128, -1);
        checkVInt(16383, -1);
        checkVInt(16384, -1);
        checkVInt(268435455, -1);
        checkVInt(268435456, -1);

    }