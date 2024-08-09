@Test
    public void testClear() {
        ByteBuffer bb1 = ByteBuffer.allocate(4);
        bb1.put("0123".getBytes());
        bb1.flip();

        ByteBuffer bb2 = ByteBuffer.allocate(4);
        bb2.put("4567".getBytes());
        bb2.flip();

        IoBuffer ioBuffer = new IoBuffer(bb1, bb2);

        // Move forward a bit
        ioBuffer.get();
        ioBuffer.get();

        // Clear
        ioBuffer.clear();

        // We should be back to the origin
        assertEquals(0, ioBuffer.position());
        assertEquals(8, ioBuffer.limit());
    }