@Test
    public void testParseVersion() {
        final String[] fileName = {"something-0.9.5.jar", "lib2-1.1.jar", "lib1.5r4-someflag-R26.jar",
            "lib-1.2.5-dev-20050313.jar", "testlib_V4.4.0.jar", "lib-core-2.0.0-RC1-SNAPSHOT.jar",
            "lib-jsp-2.0.1_R114940.jar", "dev-api-2.3.11_R121413.jar", "lib-api-3.7-SNAPSHOT.jar",
            "-", "", "1.3-beta", "6", "openssl1.0.1c", "jsf-impl-2.2.8-02.jar"};
        final String[] expResult = {"0.9.5", "1.1", "1.5.r4", "1.2.5", "4.4.0", "2.0.0.rc1",
            "2.0.1.r114940", "2.3.11.r121413", "3.7", "-", null, "1.3.beta", "6", "1.0.1c", "2.2.8.02"};

        for (int i = 0; i < fileName.length; i++) {
            final DependencyVersion version = DependencyVersionUtil.parseVersion(fileName[i]);
            String result = null;
            if (version != null) {
                result = version.toString();
            }
            assertEquals("Failed extraction on \"" + fileName[i] + "\".", expResult[i], result);
        }

        String[] failingNames = {"no-version-identified.jar", "somelib-04aug2000r7-dev.jar", /*"no.version15.jar",*/
            "lib_1.0_spec-1.1.jar", "lib-api_1.0_spec-1.0.1.jar"};
        for (String failingName : failingNames) {
            final DependencyVersion version = DependencyVersionUtil.parseVersion(failingName);
            assertNull("Found version in name that should have failed \"" + failingName + "\".", version);
        }
    }