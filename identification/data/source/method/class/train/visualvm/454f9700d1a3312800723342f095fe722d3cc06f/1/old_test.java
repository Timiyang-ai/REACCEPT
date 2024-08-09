    @Test
    public void setInterval() {
        System.out.println("setInterval");
        Quantum interval = Quantum.SUSPENDED;
        assertEquals(DEFAULT_INTERVAL, instance.getInterval());
        instance.setInterval(interval);
        assertEquals(interval, instance.getInterval());
    }