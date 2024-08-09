@SuppressWarnings("nls")
    @Test
    public void testIdFromName() {
        assertIdFromName("EricWittmann", "Eric Wittmann");
        assertIdFromName("DeloitteTouche", "Deloitte & Touche");
        assertIdFromName("JBoss_Overlord", "JBoss_Overlord");
        assertIdFromName("Red--Hat", "!Red--Hat?");
        assertIdFromName("Org.With.Periods", "Org.With.Periods");
        assertIdFromName("my-project", "my-project");
        assertIdFromName("MyInjectionimgsrca.fsdn.comsdtopicsspace_64.pngaltSpacetitleSpaceheight64width64", 
                "My Injection: <img src=\\\"//a.fsdn.com/sd/topics/space_64.png\\\" alt=\\\"Space\\\" title=\\\"Space\\\" height=\\\"64\\\" width=\\\"64\\\">");
        assertIdFromName("1.0.7-SNAPSHOT", "1.0.7-SNAPSHOT");
        assertIdFromName("2.1.0_Final", "2.1.0_Final");
        assertIdFromName("Teparados", "Té para dos");
        assertIdFromName("Cajdladvoih", "Чай для двоих");
    }