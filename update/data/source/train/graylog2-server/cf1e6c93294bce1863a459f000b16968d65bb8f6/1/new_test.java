@Test
    public void testGetHostgroupId() {
        DBObject m = this.buildMongoHostgroupHost();
        HostgroupHost hh = new HostgroupHost(m);
        assertEquals(m.get("hostgroup_id"), hh.getHostgroupId());
    }