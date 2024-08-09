public Object execute(CommandLine commandLine)
    throws Exception
  {
    if(version == null){
      PluginResources resources = Services.getPluginResources("org.eclim");
      String eclim_name = "eclim";
      String eclipse_name = "eclipse";
      int pad = Math.max(eclim_name.length(), eclipse_name.length());

      String eclim_version = StringUtils.rightPad(eclim_name, pad) + ' ' +
        resources.getProperty("pluginVersion");

      String eclipse_version = getVersion();
      eclipse_version = StringUtils.rightPad(
          eclipse_name, pad) + ' ' + eclipse_version;

      version = eclim_version + '\n' + eclipse_version;
    }

    return version;
  }