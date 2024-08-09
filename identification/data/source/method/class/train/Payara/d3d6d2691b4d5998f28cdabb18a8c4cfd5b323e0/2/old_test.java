    @Test
    public void convertStringToPropertiesTest() {
        String propsStr = "prop1=valA:prop2=valB:prop3=valC";
        Properties propsExpected = new Properties();
        propsExpected.put("prop1", "valA");
        propsExpected.put("prop2", "valB");
        propsExpected.put("prop3", "valC");
        Properties propsActual =
            MapInjectionResolver.convertStringToProperties(propsStr, ':');
        assertEquals(propsExpected, propsActual);
    }