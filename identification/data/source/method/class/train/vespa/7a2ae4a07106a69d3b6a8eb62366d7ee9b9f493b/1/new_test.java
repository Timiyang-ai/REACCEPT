    @Test
    public void freeIPs() {
        assertEquals(2, capacity.freeIPs(host1));
        assertEquals(1, capacity.freeIPs(host2));
        assertEquals(0, capacity.freeIPs(host3));
    }