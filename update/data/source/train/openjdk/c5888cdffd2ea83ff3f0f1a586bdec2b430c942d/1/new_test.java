@Test
    void testModuleSummary() {
        javadoc("-d", "out-moduleSummary", "-use", "-Xdoclint:none",
                "--module-source-path", testSrc,
                "--module", "moduleA,moduleB",
                "testpkgmdlA", "testpkgmdlB", "moduleB/testpkg2mdlB");
        checkExit(Exit.OK);
        checkModuleSummary();
        checkNegatedModuleSummary();
    }