    @Test
    public void setCapacity() {
        RingbufferConfig config = new RingbufferConfig(NAME);

        config.setCapacity(1000);

        assertEquals(1000, config.getCapacity());
    }