@Test
    public void testFindLocations() throws Exception {
        File test1 = new File(Constants.TEST_TMP_ROOT, "test1");

        try {

            bc = new TestBootstrapConfig();
            bc.findLocations(null, null, null, null, null);

            bc.printLocations(false); // print locations: no formatting
            assertTrue("Bootstrap lib dir should be a directory (not a jar)", bc.bootstrapLib.isDirectory());

            checkDirs("A", bc);
            assertEquals("A: Default server name", BootstrapConstants.DEFAULT_SERVER_NAME, bc.getProcessName());
            assertEquals("A: installRoot should be parent of bootstrap lib", bc.installRoot, bc.bootstrapLib.getParentFile());
            assertEquals("A: userRoot should be child of installRoot", bc.installRoot, bc.userRoot.getParentFile());
            assertEquals("A: processesRoot should be a child of the userRoot", bc.userRoot, bc.processesRoot.getParentFile());
            assertEquals("A: configDir should be a child of the processesRoot", bc.processesRoot, bc.configDir.getParentFile());
            assertSame("A: outputRoot should be same as processesRoot", bc.processesRoot, bc.outputRoot);
            assertSame("A: outputDir should be same as configDir", bc.configDir, bc.outputDir);

            bc = new TestBootstrapConfig();
            bc.findLocations(testName.getMethodName(), test1.getAbsolutePath(), null, null, null);

            checkDirs("B", bc);
            assertEquals("B: userRoot should match userDir parameter", test1.getCanonicalFile(), bc.userRoot.getCanonicalFile());
            assertEquals("B: processesRoot should be a child of the userRoot", bc.userRoot, bc.processesRoot.getParentFile());
            assertEquals("B: configDir should be a child of the processesRoot", bc.processesRoot, bc.configDir.getParentFile());
            assertEquals("B: getServerFile(null) should return configDir", bc.configDir, bc.getConfigFile(null));

            assertSame("B: outputRoot should be same as processesRoot", bc.processesRoot, bc.outputRoot);
            assertSame("B: outputDir should be same as configDir", bc.configDir, bc.outputDir);
            assertEquals("B: getServerOutputFile(null) should return outputDir", bc.outputDir, bc.getOutputFile(null));

            // Now test for the output dir split: we now have two trees... (one shorter
            // than the other.. )
            bc = new TestBootstrapConfig();
            bc.findLocations(testName.getMethodName(), null, test1.getAbsolutePath(), null, null);

            checkDirs("C", bc);
            assertEquals("C: userRoot should be child of installRoot", bc.installRoot, bc.userRoot.getParentFile());
            assertEquals("C: processesRoot should be a child of the userRoot", bc.userRoot, bc.processesRoot.getParentFile());
            assertEquals("C: configDir should be a child of the processesRoot", bc.processesRoot, bc.configDir.getParentFile());
            assertEquals("C: getServerFile(null) should return configDir", bc.configDir, bc.getConfigFile(null));

            assertEquals("C: outputRoot should match outputDir parameter", test1.getCanonicalFile(), bc.outputRoot.getCanonicalFile());
            assertEquals("C: outputDir should be a child of the outputRoot", bc.outputRoot, bc.outputDir.getParentFile());
            assertEquals("C: getServerOutputFile(null) should return outputDir", bc.outputDir, bc.getOutputFile(null));

            assertEquals("C: getLogDiretory() should be a child of outputDir", bc.getOutputFile("logs"), bc.getLogDirectory());

            // Now test for a separate log directory
            bc = new TestBootstrapConfig();
            bc.findLocations(testName.getMethodName(), null, null, test1.getAbsolutePath(), null);

            checkDirs("D", bc);
            assertEquals("D: userRoot should be child of installRoot", bc.installRoot, bc.userRoot.getParentFile());
            assertEquals("D: processesRoot should be a child of the userRoot", bc.userRoot, bc.processesRoot.getParentFile());
            assertEquals("D: configDir should be a child of the processesRoot", bc.processesRoot, bc.configDir.getParentFile());
            assertEquals("D: getServerFile(null) should return configDir", bc.configDir, bc.getConfigFile(null));

            assertSame("D: outputRoot should be same as processesRoot", bc.processesRoot, bc.outputRoot);
            assertSame("D: outputDir should be same as configDir", bc.configDir, bc.outputDir);
            assertEquals("D: getServerOutputFile(null) should return outputDir", bc.outputDir, bc.getOutputFile(null));

            assertEquals("D: getLogDiretory() should match logDir parameter", test1.getCanonicalFile(), bc.getLogDirectory().getCanonicalFile());

            initProps.clear();

            // Make sure system properties are ignored
            System.setProperty(BootstrapConstants.LOC_PROPERTY_INSTANCE_DIR, test1.getAbsolutePath());
            bc = new TestBootstrapConfig();
            // configure(map, userDir, outputDir, logDir)
            bc.findLocations(testName.getMethodName(), null, null, null, null);

            // This set should be identical to the conditions used in A (i.e. the defaults,
            //  as null is passed in as a parameters)
            checkDirs("E", bc);
            assertEquals("E: userRoot should be child of installRoot", bc.installRoot, bc.userRoot.getParentFile());
            assertEquals("E: processesRoot should be a child of the userRoot", bc.userRoot, bc.processesRoot.getParentFile());
            assertEquals("E: configDir should be a child of the processesRoot", bc.processesRoot, bc.configDir.getParentFile());
            assertSame("E: outputRoot should be same as processesRoot", bc.processesRoot, bc.outputRoot);
            assertSame("E: outputDir should be same as configDir", bc.configDir, bc.outputDir);

        } finally {
            TestUtils.cleanTempFiles(test1);
            System.clearProperty(BootstrapConstants.LOC_PROPERTY_INSTANCE_DIR);
            System.clearProperty(BootstrapConstants.LOC_PROPERTY_INSTALL_DIR);
        }
    }