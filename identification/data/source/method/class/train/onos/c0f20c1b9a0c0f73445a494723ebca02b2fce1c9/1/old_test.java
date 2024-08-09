    @Test
    public void load() {
        title("load");
        cache.load();
        print(cache.dumpString());

        // See mock service bundle for expected values (AbstractTopoModelTest)
        assertEquals("unex # cnodes", 3, cache.clusterMemberCount());
        assertEquals("unex # regions", 3, cache.regionCount());
        assertEquals("unex # devices", 9, cache.deviceCount());
        assertEquals("unex # hosts", 18, cache.hostCount());
        assertEquals("unex # device-links", 8, cache.deviceLinkCount());
        assertEquals("unex # edge-links", 18, cache.edgeLinkCount());
        assertEquals("unex # synth-links", 0, cache.synthLinkCount());
    }