@Test
  public void testGetProperties() {
    Map<String, Set<HashMap<String, String>>> properties = new HashMap<>();
    Set<HashMap<String, String>> setting1 = new HashSet<>();
    Set<HashMap<String, String>> setting2 = new HashSet<>();
    Set<HashMap<String, String>> setting3 = new HashSet<>();
    Set<HashMap<String, String>> setting4 = new HashSet<>();

    // Setting 1: Property1
    HashMap<String, String> setting1Properties1 = new HashMap<>();
    setting1Properties1.put(Setting.SETTING_NAME_RECOVERY_ENABLED, "true");
    setting1.add(setting1Properties1);

    // Setting 2: Property1 and Property2
    HashMap<String, String> setting2Properties1 = new HashMap<>();
    setting2Properties1.put(Setting.SETTING_NAME_NAME, "HDFS");
    setting2Properties1.put(Setting.SETTING_NAME_RECOVERY_ENABLED, "false");

    HashMap<String, String> setting2Properties2 = new HashMap<>();
    setting2Properties2.put(Setting.SETTING_NAME_NAME, "TEZ");
    setting2Properties2.put(Setting.SETTING_NAME_RECOVERY_ENABLED, "false");

    setting2.add(setting2Properties1);
    setting2.add(setting2Properties2);

    //Setting 3: Property 1
    HashMap<String, String> setting3Properties1 = new HashMap<>();
    setting1Properties1.put(Setting.SETTING_NAME_SKIP_FAILURE, "true");
    setting1.add(setting3Properties1);

    //Setting 4: Property 1 and 2
    HashMap<String, String> setting4Properties1 = new HashMap<>();
    setting4Properties1.put(RepositorySetting.OVERRIDE_STRATEGY, RepositorySetting.OVERRIDE_STRATEGY_ALWAYS_APPLY);
    setting4Properties1.put(RepositorySetting.OPERATING_SYSTEM, "redhat7");
    setting4Properties1.put(RepositorySetting.REPO_ID, "HDP");
    setting4Properties1.put(RepositorySetting.BASE_URL, "http://localhost/repo");
    setting4.add(setting4Properties1);

    HashMap<String, String> setting4Properties2 = new HashMap<>();
    setting4Properties2.put(RepositorySetting.OVERRIDE_STRATEGY, RepositorySetting.OVERRIDE_STRATEGY_ALWAYS_APPLY);
    setting4Properties2.put(RepositorySetting.OPERATING_SYSTEM, "redhat7");
    setting4Properties2.put(RepositorySetting.REPO_ID, "HDP-UTIL");
    setting4Properties2.put(RepositorySetting.BASE_URL, "http://localhost/repo");
    setting4.add(setting4Properties2);

    properties.put(Setting.SETTING_NAME_RECOVERY_SETTINGS, setting1);
    properties.put(Setting.SETTING_NAME_SERVICE_SETTINGS, setting2);
    properties.put(Setting.SETTING_NAME_DEPLOYMENT_SETTINGS, setting3);
    properties.put(Setting.SETTING_NAME_REPOSITORY_SETTINGS, setting4);

    Setting setting = new Setting(properties);
    assertEquals(properties, setting.getProperties());
  }