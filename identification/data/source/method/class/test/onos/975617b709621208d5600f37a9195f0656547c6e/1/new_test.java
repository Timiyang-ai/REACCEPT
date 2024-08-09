    @Test
    public void merge() {
        DefaultDriver one = new DefaultDriver("foo.bar", new ArrayList<>(), "Circus", "lux", "1.2a",
                                              ImmutableMap.of(TestBehaviour.class,
                                                              TestBehaviourImpl.class),
                                              ImmutableMap.of("foo", "bar"));
        Driver ddc =
                one.merge(new DefaultDriver("foo.bar", new ArrayList<>(), "", "", "",
                                            ImmutableMap.of(TestBehaviourTwo.class,
                                                            TestBehaviourTwoImpl.class),
                                            ImmutableMap.of("goo", "wee")));

        assertEquals("incorrect name", "foo.bar", ddc.name());
        assertEquals("incorrect mfr", "Circus", ddc.manufacturer());
        assertEquals("incorrect hw", "lux", ddc.hwVersion());
        assertEquals("incorrect sw", "1.2a", ddc.swVersion());

        assertEquals("incorrect behaviour count", 2, ddc.behaviours().size());
        assertTrue("incorrect behaviour", ddc.hasBehaviour(TestBehaviourTwo.class));

        assertEquals("incorrect property count", 2, ddc.properties().size());
        assertEquals("incorrect key count", 2, ddc.keys().size());
        assertEquals("incorrect property", "wee", ddc.value("goo"));

        assertTrue("incorrect toString", ddc.toString().contains("Circus"));
    }