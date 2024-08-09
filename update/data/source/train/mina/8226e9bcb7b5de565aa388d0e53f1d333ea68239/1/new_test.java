@Test
    public void testClearEmptyBuffer() {
        ByteBuffer bb1 = ByteBuffer.allocate(4);
        bb1.put("012".getBytes());
        bb1.flip();

        ByteBuffer bb2 = ByteBuffer.allocate(4);
        bb2.put("345".getBytes());
        bb2.flip();

        IoBuffer ioBuffer = new IoBuffer(bb1, bb2);

        assertEquals(6, ioBuffer.limit());

        // Move forward a bit
        ioBuffer.get();
        ioBuffer.get();

        // Clear
        ioBuffer.clear();

        // We should be back to the origin
        assertEquals(0, ioBuffer.position());

        // The limit must have grown
        assertEquals(8, ioBuffer.limit());
    }