@Test
    public void testScanArtifacts() throws DatabaseException, InvalidSettingException {
        //TODO get this to work under JDK 1.8
        if (canRun()) {
            MavenProject project = new MockUp<MavenProject>() {
                @Mock
                public Set<Artifact> getArtifacts() {
                    Set<Artifact> artifacts = new HashSet<Artifact>();
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
            }.getMockInstance();

            boolean autoUpdate = Settings.getBoolean(Settings.KEYS.AUTO_UPDATE);
            Settings.setBoolean(Settings.KEYS.AUTO_UPDATE, false);
            MavenEngine engine = new MavenEngine(null, null);
            Settings.setBoolean(Settings.KEYS.AUTO_UPDATE, autoUpdate);

            assertTrue(engine.getDependencies().isEmpty());
            BaseDependencyCheckMojoImpl instance = new BaseDependencyCheckMojoImpl();
            instance.scanArtifacts(project, engine);
            assertFalse(engine.getDependencies().isEmpty());
            engine.cleanup();
        }
    }