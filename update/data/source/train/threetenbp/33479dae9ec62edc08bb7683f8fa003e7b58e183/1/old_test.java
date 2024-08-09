@Test(groups={"tck"})
    public void test_getRules() {
        ZoneRulesProvider group = ZoneRulesProvider.getProvider("TZDB");
        ZoneRules rules = group.getRules("Europe/London", "2008i");
        assertEquals(rules.getTransitionRules().size(), 2);
        assertEquals(rules.getTransitionRules().get(0).getDayOfWeek(), DayOfWeek.SUNDAY);
        assertEquals(rules.getTransitionRules().get(0).getDayOfMonthIndicator(), 25);
        assertEquals(rules.getTransitionRules().get(0).getMonth(), Month.MARCH);
        assertEquals(rules.getTransitionRules().get(1).getDayOfWeek(), DayOfWeek.SUNDAY);
        assertEquals(rules.getTransitionRules().get(1).getDayOfMonthIndicator(), 25);
        assertEquals(rules.getTransitionRules().get(1).getMonth(), Month.OCTOBER);
    }