@Test
    public void addAllAsync_manyTimesRoundTheRing() throws Exception {
        RingbufferConfig c = config.getRingbufferConfig(ringbuffer.getName());
        Random random = new Random();

        for (int iteration = 0; iteration < 1000; iteration++) {
            List<String> items = randomList(max(1, random.nextInt(c.getCapacity())));

            long previousTailSeq = ringbuffer.tailSequence();

            long result = ringbuffer.addAllAsync(items, OVERWRITE).get();

            assertEquals(previousTailSeq + items.size(), ringbuffer.tailSequence());

            if (ringbuffer.tailSequence() < c.getCapacity()) {
                assertEquals(0, ringbuffer.headSequence());
            } else {
                assertEquals(ringbuffer.tailSequence() - c.getCapacity() + 1, ringbuffer.headSequence());
            }
            assertEquals(ringbuffer.tailSequence(), result);

            long startSequence = previousTailSeq + 1;
            for (int k = 0; k < items.size(); k++) {
                assertEquals(items.get(k), ringbuffer.readOne(startSequence + k));
            }
        }
    }