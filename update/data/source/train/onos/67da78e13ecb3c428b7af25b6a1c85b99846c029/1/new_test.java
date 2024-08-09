@Test
    public void testRemoveIp() {
        Set<IpAddress> ips = new HashSet<>();
        ips.add(IP1);
        ips.add(IP2);

        HostDescription description = createHostDesc(HOSTID, ips);
        ecXHostStore.createOrUpdateHost(PID, HOSTID, description, false);
        ecXHostStore.removeIp(HOSTID, IP1);
        Host host = ecXHostStore.getHost(HOSTID);

        assertFalse(host.ipAddresses().contains(IP1));
        assertTrue(host.ipAddresses().contains(IP2));
    }