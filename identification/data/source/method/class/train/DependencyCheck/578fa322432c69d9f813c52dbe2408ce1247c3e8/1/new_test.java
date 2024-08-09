@Test
    public void testShouldUpdate() throws Exception {
        DatabaseProperties properties = new MockUp<DatabaseProperties>() {
            final private Properties properties = new Properties();

            @Mock
            public void save(String key, String value) throws UpdateException {
                properties.setProperty(key, value);
            }

            @Mock
            public String getProperty(String key) {
                return properties.getProperty(key);
            }

        }.getMockInstance();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String updateToVersion = "1.2.6";
        String currentVersion = "1.2.6";
        long lastChecked = df.parse("2014-12-01").getTime();
        long now = df.parse("2014-12-01").getTime();

        EngineVersionCheck instance = new EngineVersionCheck();
        boolean expResult = false;
        instance.setUpdateToVersion(updateToVersion);
        boolean result = instance.shouldUpdate(lastChecked, now, properties, currentVersion);
        assertEquals(expResult, result);

        updateToVersion = "1.2.5";
        currentVersion = "1.2.5";
        lastChecked = df.parse("2014-10-01").getTime();
        now = df.parse("2014-12-01").getTime();
        expResult = true;
        instance.setUpdateToVersion(updateToVersion);
        result = instance.shouldUpdate(lastChecked, now, properties, currentVersion);
        assertEquals(expResult, result);
        //System.out.println(properties.getProperty(CURRENT_ENGINE_RELEASE));

        updateToVersion = "1.2.5";
        currentVersion = "1.2.5";
        lastChecked = df.parse("2014-12-01").getTime();
        now = df.parse("2014-12-03").getTime();
        expResult = false;
        instance.setUpdateToVersion(updateToVersion);
        result = instance.shouldUpdate(lastChecked, now, properties, currentVersion);
        assertEquals(expResult, result);

        updateToVersion = "1.2.6";
        currentVersion = "1.2.5";
        lastChecked = df.parse("2014-12-01").getTime();
        now = df.parse("2014-12-03").getTime();
        expResult = true;
        instance.setUpdateToVersion(updateToVersion);
        result = instance.shouldUpdate(lastChecked, now, properties, currentVersion);
        assertEquals(expResult, result);

        updateToVersion = "1.2.5";
        currentVersion = "1.2.6";
        lastChecked = df.parse("2014-12-01").getTime();
        now = df.parse("2014-12-08").getTime();
        expResult = false;
        instance.setUpdateToVersion(updateToVersion);
        result = instance.shouldUpdate(lastChecked, now, properties, currentVersion);
        assertEquals(expResult, result);

        updateToVersion = "";
        currentVersion = "1.2.5";
        lastChecked = df.parse("2014-12-01").getTime();
        now = df.parse("2014-12-03").getTime();
        expResult = false;
        instance.setUpdateToVersion(updateToVersion);
        result = instance.shouldUpdate(lastChecked, now, properties, currentVersion);
        assertEquals(expResult, result);

        updateToVersion = "";
        currentVersion = "1.2.5";
        lastChecked = df.parse("2014-12-01").getTime();
        now = df.parse("2015-12-08").getTime();
        expResult = true;
        instance.setUpdateToVersion(updateToVersion);
        result = instance.shouldUpdate(lastChecked, now, properties, currentVersion);
        assertEquals(expResult, result);
    }