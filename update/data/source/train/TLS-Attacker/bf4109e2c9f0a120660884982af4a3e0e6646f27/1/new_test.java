@Test
    public void testParseRecords() {
        Random r = RandomHelper.getRandom();
        for (int i = 0; i < 1000000; i++) {
            byte[] data = new byte[r.nextInt(1000000)];
            r.nextBytes(data);
            layer.parseRecords(data);
        }
    }