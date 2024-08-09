public Object execute(CommandLine commandLine)
    throws Exception
  {
    if(versions == null){
      PluginResources resources = Services.getPluginResources("org.eclim");

      versions = new HashMap<String,String>();
      versions.put("eclim", resources.getProperty("pluginVersion"));
      versions.put("eclipse", getVersion());
    }

    return versions;
  }