public String execute (CommandLine _commandLine)
    throws Exception
  {
    if(version == null){
      PluginResources resources = Services.getPluginResources();
      String eclim_name = resources.getProperty("pluginName");
      String eclipse_name = "eclipse";
      int pad = Math.max(eclim_name.length(), eclipse_name.length());

      String eclim_version = StringUtils.rightPad(eclim_name, pad) + ' ' +
        resources.getProperty("pluginVersion");

      Dictionary headers = ResourcesPlugin.getPlugin().getBundle().getHeaders();
      String eclipse_version = StringUtils.rightPad(eclipse_name, pad) + ' ' +
        headers.get("Bundle-Version");

      version = eclim_version + '\n' + eclipse_version;
    }

    return version;
  }