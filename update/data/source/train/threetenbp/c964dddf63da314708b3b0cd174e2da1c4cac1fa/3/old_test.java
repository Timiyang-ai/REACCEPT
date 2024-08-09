@Test(groups={"tck"})
    public void test_createTransition_floatingWeek_gap_notEndOfDay() {
        ZoneOffsetTransitionRule test = new ZoneOffsetTransitionRule(
                Month.MARCH, 20, DayOfWeek.SUNDAY, TIME_0100, false, TimeDefinition.WALL,
                OFFSET_0200, OFFSET_0200, OFFSET_0300);
        ZoneOffsetTransition trans = new ZoneOffsetTransition(
                OffsetDateTime.of(2000, Month.MARCH, 26, 1, 0, OFFSET_0200), OFFSET_0300);
        assertEquals(test.createTransition(2000), trans);
    }