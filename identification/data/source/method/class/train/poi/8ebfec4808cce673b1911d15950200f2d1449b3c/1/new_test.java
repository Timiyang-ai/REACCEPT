@Test
    public void testWrite1() throws IOException {
        final byte[] expected = data(25);

        POIFSWriterListener l = (event) -> {
            DocumentOutputStream dstream = event.getStream();

            try {
                for (byte b : expected) {
                    dstream.write((int)b);
                }
            } catch (IOException ignored) {
                fail("stream exhausted too early");
            }

            try {
                dstream.write(0);
                fail("Should have caught IOException");
            }
            catch (IOException ignored) {
            }
        };

        compare(l, expected);
    }