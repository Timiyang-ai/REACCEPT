@Test
    public void testProcess() {
        //File struts = new File(this.getClass().getClassLoader().getResource("struts2-core-2.1.2.jar").getPath());
        File struts = BaseTest.getResourceAsFile(this, "struts2-core-2.1.2.jar");
        Dependency dependency = new Dependency(struts);
        dependency.addIdentifier("cpe", "cpe:/a:microsoft:.net_framework:4.5", "some url not needed for this test");
        String sha1 = dependency.getSha1sum();
        dependency.setSha1sum("384FAA82E193D4E4B0546059CA09572654BC3970");
        Vulnerability v = createVulnerability();
        dependency.addVulnerability(v);

        //cwe
        SuppressionRule instance = new SuppressionRule();
        instance.setSha1(sha1);
        instance.addCwe("287");
        instance.process(dependency);
        assertEquals(1, dependency.getVulnerabilities().size());
        dependency.setSha1sum(sha1);
        instance.process(dependency);
        assertTrue(dependency.getVulnerabilities().isEmpty());
        assertEquals(1, dependency.getSuppressedVulnerabilities().size());

        //cvss
        dependency.addVulnerability(v);
        instance = new SuppressionRule();
        instance.addCvssBelow(5f);
        instance.process(dependency);
        assertEquals(1, dependency.getVulnerabilities().size());
        instance.addCvssBelow(8f);
        instance.process(dependency);
        assertTrue(dependency.getVulnerabilities().isEmpty());
        assertEquals(1, dependency.getSuppressedVulnerabilities().size());

        //cve
        dependency.addVulnerability(v);
        instance = new SuppressionRule();
        instance.addCve("CVE-2012-1337");
        instance.process(dependency);
        assertEquals(1, dependency.getVulnerabilities().size());
        instance.addCve("CVE-2013-1337");
        instance.process(dependency);
        assertTrue(dependency.getVulnerabilities().isEmpty());
        assertEquals(1, dependency.getSuppressedVulnerabilities().size());

        //cpe
        instance = new SuppressionRule();
        PropertyType pt = new PropertyType();
        pt.setValue("cpe:/a:microsoft:.net_framework:4.0");
        instance.addCpe(pt);
        instance.process(dependency);
        assertTrue(dependency.getIdentifiers().size() == 1);
        pt = new PropertyType();
        pt.setValue("cpe:/a:microsoft:.net_framework:4.5");
        instance.addCpe(pt);
        pt = new PropertyType();
        pt.setValue(".*");
        pt.setRegex(true);
        instance.setFilePath(pt);
        instance.process(dependency);
        assertTrue(dependency.getIdentifiers().isEmpty());
        assertEquals(1, dependency.getSuppressedIdentifiers().size());

        instance = new SuppressionRule();
        dependency.addIdentifier("cpe", "cpe:/a:microsoft:.net_framework:4.0", "some url not needed for this test");
        dependency.addIdentifier("cpe", "cpe:/a:microsoft:.net_framework:4.5", "some url not needed for this test");
        dependency.addIdentifier("cpe", "cpe:/a:microsoft:.net_framework:5.0", "some url not needed for this test");
        pt = new PropertyType();
        pt.setValue("cpe:/a:microsoft:.net_framework");
        instance.addCpe(pt);
        instance.setBase(true);
        assertEquals(3, dependency.getIdentifiers().size());
        assertEquals(1, dependency.getSuppressedIdentifiers().size());
        instance.process(dependency);
        assertTrue(dependency.getIdentifiers().isEmpty());
        assertEquals(1, dependency.getSuppressedIdentifiers().size());
    }