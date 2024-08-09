@Test
    public void testScanArtifacts() throws DatabaseException, InvalidSettingException {
        new MockUp<MavenProject>() {
            @Mock
            public Set<Artifact> getArtifacts() {
                Set<Artifact> artifacts = new HashSet<>();
                Artifact a = new ArtifactStub();
                try {
                    File file = new File(Test.class.getProtectionDomain().getCodeSource().getLocation().toURI());
                    a.setFile(file);
                    artifacts.add(a);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(BaseDependencyCheckMojoTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                //File file = new File(this.getClass().getClassLoader().getResource("daytrader-ear-2.1.7.ear").getPath());

                return artifacts;
            }

            @Mock
            public String getName() {
                return "test-project";
            }
        };

        if (canRun()) {
            boolean autoUpdate = getSettings().getBoolean(Settings.KEYS.AUTO_UPDATE);
            getSettings().setBoolean(Settings.KEYS.AUTO_UPDATE, false);
            try (Engine engine = new Engine(getSettings())) {
                getSettings().setBoolean(Settings.KEYS.AUTO_UPDATE, autoUpdate);

                assertTrue(engine.getDependencies().length == 0);
                BaseDependencyCheckMojoImpl instance = new BaseDependencyCheckMojoImpl();
                try { //the mock above fails under some JDKs
                    instance.scanArtifacts(project, engine);
                } catch (NullPointerException ex) {
                    Assume.assumeNoException(ex);
                }
                assertFalse(engine.getDependencies().length == 0);
            }
        }
    }