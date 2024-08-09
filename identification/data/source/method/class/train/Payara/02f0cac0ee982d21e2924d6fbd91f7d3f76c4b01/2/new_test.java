    @Test
    public void extractParametersTest() {
        ParameterMap props = aa.extractParameters("uniquetablenames=false&createtables=true&target=server&libraries=foo.jar&dbvendorname=test&deploymentplan=test");
        Properties correctProps = new Properties();
        correctProps.put("uniquetablenames", "false");
        correctProps.put("createtables", "true");
        correctProps.put("target", "server");
        correctProps.put("libraries", "foo.jar");
        correctProps.put("dbvendorname", "test");
        correctProps.put("deploymentplan", "test");
        for (String prop : correctProps.stringPropertyNames()) {
            assertEquals("compare Properties",
                correctProps.getProperty(prop), props.getOne(prop));
        }
    }