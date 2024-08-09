@SuppressWarnings("nls")
    @Test
    public void testIdFromName() {
        assertIdFromName("EricWittmann", "Eric Wittmann");
        assertIdFromName("DeloitteTouche", "Deloitte & Touche");
        assertIdFromName("JBoss_Overlord", "JBoss_Overlord");
        assertIdFromName("Red--Hat", "!Red--Hat?");
        assertIdFromName("Org.With.Periods", "Org.With.Periods");
        assertIdFromName("my-project", "my-project");
    }