public Object execute(CommandLine commandLine)
    throws Exception
  {
    Option[] options = getPreferences().getOptions();
    return SettingsFilter.instance.filter(commandLine, options);
  }