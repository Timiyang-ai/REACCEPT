public Object execute(CommandLine commandLine)
    throws Exception
  {
    Option[] options = getPreferences().getOptions();
    Arrays.sort(options);
    return options;
  }