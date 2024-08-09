@Test
    public void testProcess() {
        File struts = new File(this.getClass().getClassLoader().getResource("struts2-core-2.1.2.jar").getPath());
        Dependency dependency = new Dependency(struts);
        dependency.addIdentifier("cwe", "cpe:/a:microsoft:.net_framework:4.5", "some url not needed for this test");
        String sha1 = dependency.getSha1sum();
        dependency.setSha1sum("384FAA82E193D4E4B0546059CA09572654BC3970");
        Vulnerability v = createVulnerability();
        dependency.addVulnerability(v);

        //cwe
        SuppressionRule instance = new SuppressionRule();
        instance.setSha1(sha1);
        instance.addCwe("287");
        instance.process(dependency);
        assertTrue(dependency.getVulnerabilities().size() == 1);
        dependency.setSha1sum(sha1);
        instance.process(dependency);
        assertTrue(dependency.getVulnerabilities().isEmpty());
        assertTrue(dependency.getSuppressedVulnerabilities().size() == 1);

        //cvss
        dependency.addVulnerability(v);
        instance = new SuppressionRule();
        instance.addCvssBelow(5f);
        instance.process(dependency);
        assertTrue(dependency.getVulnerabilities().size() == 1);
        instance.addCvssBelow(8f);
        instance.process(dependency);
        assertTrue(dependency.getVulnerabilities().isEmpty());
        assertTrue(dependency.getSuppressedVulnerabilities().size() == 1);

        //cve
        dependency.addVulnerability(v);
        instance = new SuppressionRule();
        instance.addCve("CVE-2012-1337");
        instance.process(dependency);
        assertTrue(dependency.getVulnerabilities().size() == 1);
        instance.addCve("CVE-2013-1337");
        instance.process(dependency);
        assertTrue(dependency.getVulnerabilities().isEmpty());
        assertTrue(dependency.getSuppressedVulnerabilities().size() == 1);

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
        assertTrue(dependency.getSuppressedIdentifiers().size() == 1);

        dependency.addIdentifier("cwe", "cpe:/a:microsoft:.net_framework:4.0", "some url not needed for this test");
        dependency.addIdentifier("cwe", "cpe:/a:microsoft:.net_framework:4.5", "some url not needed for this test");
        dependency.addIdentifier("cwe", "cpe:/a:microsoft:.net_framework:5.0", "some url not needed for this test");
        pt = new PropertyType();
        pt.setValue("cpe:/a:microsoft:.net_framework");
        instance.addCpe(pt);
        assertTrue(dependency.getIdentifiers().size() == 3);
        instance.process(dependency);
        assertTrue(dependency.getIdentifiers().isEmpty());
        assertTrue(dependency.getSuppressedIdentifiers().size() == 3);
    }