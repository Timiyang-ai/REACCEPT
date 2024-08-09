    @Test
    public void read()
            throws Exception
    {
        ByteArrayOutputStream compressedBuf = new ByteArrayOutputStream();
        SnappyOutputStream snappyOut = new SnappyOutputStream(compressedBuf);
        byte[] orig = readResourceFile("alice29.txt");
        snappyOut.write(orig);
        snappyOut.close();
        byte[] compressed = compressedBuf.toByteArray();
        _logger.debug("compressed size: " + compressed.length);

        SnappyInputStream in = new SnappyInputStream(new ByteArrayInputStream(compressed));
        byte[] uncompressed = readFully(in);

        assertEquals(orig.length, uncompressed.length);
        assertArrayEquals(orig, uncompressed);
    }