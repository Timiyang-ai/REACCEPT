@Test
    public void testWrite() throws Exception {
        String segmentName = "foo";
        int appendCount = 100;

        try (Storage s = createStorage()) {
            // Check pre-create write.
            assertThrows("write() did not throw for non-existent StreamSegment.",
                         s.write(segmentName, 0, new ByteArrayInputStream(new byte[1]), 1, TIMEOUT),
                         ex -> ex instanceof StreamSegmentNotExistsException);

            s.create(segmentName, TIMEOUT).join();

            long offset = 0;
            for (int j = 0; j < appendCount; j++) {
                byte[] writeData = String.format("Segment_%s_Append_%d", segmentName, j).getBytes();
                ByteArrayInputStream dataStream = new ByteArrayInputStream(writeData);
                s.write(segmentName, offset, dataStream, writeData.length, TIMEOUT).join();
                offset += writeData.length;
            }

            // Check bad offset.
            assertThrows("write() did not throw bad offset write (smaller).",
                         s.write(segmentName, offset - 1, new ByteArrayInputStream("h".getBytes()), 1, TIMEOUT),
                         ex -> ex instanceof BadOffsetException);

            assertThrows("write() did not throw bad offset write (larger).",
                         s.write(segmentName, offset + 1, new ByteArrayInputStream("h".getBytes()), 1, TIMEOUT),
                         ex -> ex instanceof BadOffsetException);

            // Check post-delete write.
            s.delete(segmentName, TIMEOUT).join();
            assertThrows("write() did not throw for a deleted StreamSegment.",
                         s.write(segmentName, 0, new ByteArrayInputStream(new byte[1]), 1, TIMEOUT),
                         ex -> ex instanceof StreamSegmentNotExistsException);
        }
    }