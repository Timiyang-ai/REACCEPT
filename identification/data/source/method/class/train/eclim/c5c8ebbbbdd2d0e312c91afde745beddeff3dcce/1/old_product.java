public String execute (CommandLine _commandLine)
    throws Exception
  {
    if(version == null){
      PluginResources resources = Services.getPluginResources();
      version = resources.getProperty("pluginName") + ' ' +
        resources.getProperty("pluginVersion");
    }

    return version;
  }