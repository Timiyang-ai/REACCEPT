    @Test(expected = StaleSequenceException.class)
    public void read_whenStaleSequence() {
        RingbufferConfig config = new RingbufferConfig("foo").setCapacity(3);
        RingbufferContainer<Data, Data> ringbuffer = getRingbufferContainer(config);

        ringbuffer.add(toData("1"));
        ringbuffer.add(toData("2"));
        ringbuffer.add(toData("3"));
        // this one will overwrite the first item
        ringbuffer.add(toData("4"));

        ringbuffer.readAsData(0);
    }