    @Test
    public void add() {
        RingbufferConfig config = new RingbufferConfig("foo").setCapacity(10);
        RingbufferContainer<Data, Data> ringbuffer = getRingbufferContainer(config);
        ringbuffer.add(toData("foo"));
        ringbuffer.add(toData("bar"));

        assertEquals(1, ringbuffer.tailSequence());
        assertEquals(0, ringbuffer.headSequence());
    }