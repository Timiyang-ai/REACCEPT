@SuppressWarnings("nls")
    @Test
    public void testIdFromName() {
        assertIdFromName("EricWittmann", "Eric Wittmann");
        assertIdFromName("DeloitteTouche", "Deloitte & Touche");
        assertIdFromName("JBoss_Overlord", "JBoss_Overlord");
        assertIdFromName("RedHat", "!Red--Hat?");
        assertIdFromName("OrgWithPeriods", "Org.With.Periods");
    }