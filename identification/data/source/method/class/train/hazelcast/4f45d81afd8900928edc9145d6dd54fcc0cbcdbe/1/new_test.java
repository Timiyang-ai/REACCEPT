    @Test
    public void remainingCapacity_whenTTLDisabled() {
        RingbufferConfig config = new RingbufferConfig("foo").setCapacity(100).setTimeToLiveSeconds(0);
        RingbufferContainer<Data, Data> ringbuffer = getRingbufferContainer(config);

        assertEquals(config.getCapacity(), ringbuffer.remainingCapacity());

        ringbuffer.add(toData("1"));
        ringbuffer.add(toData("2"));
        assertEquals(config.getCapacity(), ringbuffer.remainingCapacity());
    }