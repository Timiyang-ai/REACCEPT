@Test(groups={"tck"})
    public void test_getRules() {
        ZoneOffset offset = ZoneOffset.ofHoursMinutesSeconds(1, 2, 3);
        assertEquals(offset.getRules().isFixedOffset(), true);
        assertEquals(offset.getRules().getOffset((Instant) null), offset);
        assertEquals(offset.getRules().getDaylightSavings((Instant) null), Duration.ZERO);
        assertEquals(offset.getRules().getStandardOffset((Instant) null), offset);
        assertEquals(offset.getRules().nextTransition((Instant) null), null);
        assertEquals(offset.getRules().previousTransition((Instant) null), null);

        assertEquals(offset.getRules().isValidOffset((LocalDateTime) null, offset), true);
        assertEquals(offset.getRules().isValidOffset((LocalDateTime) null, ZoneOffset.UTC), false);
        assertEquals(offset.getRules().isValidOffset((LocalDateTime) null, null), false);
        assertEquals(offset.getRules().getOffset((LocalDateTime) null), offset);
        assertEquals(offset.getRules().getValidOffsets((LocalDateTime) null), Arrays.asList(offset));
        assertEquals(offset.getRules().getTransition((LocalDateTime) null), null);
        assertEquals(offset.getRules().getTransitions().size(), 0);
        assertEquals(offset.getRules().getTransitionRules().size(), 0);
    }