public static int getConf(String... args) {
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd;

    try {
      cmd = parser.parse(OPTIONS, args, true /* stopAtNonOption */);
    } catch (ParseException e) {
      printHelp("Unable to parse input args: " + e.getMessage());
      return 1;
    }
    Preconditions.checkNotNull(cmd, "Unable to parse input args");
    args = cmd.getArgs();
    switch (args.length) {
      case 0:
        for (Entry<String, String> entry : Configuration.toMap().entrySet()) {
          String key = entry.getKey();
          String value = entry.getValue();
          System.out.println(String.format("%s=%s", key, value));
        }
        break;
      case 1:
        if (!PropertyKey.isValid(args[0])) {
          printHelp(String.format("%s is not a valid configuration key", args[0]));
          return 1;
        }
        PropertyKey key = PropertyKey.fromString(args[0]);
        if (!Configuration.containsKey(key)) {
          System.out.println("");
        } else {
          if (cmd.hasOption(UNIT_OPTION_NAME)) {
            String arg = cmd.getOptionValue(UNIT_OPTION_NAME).toUpperCase();
            try {
              if(arg.endsWith("B")){
                Unit unit;
                unit = Unit.valueOf(arg);
                System.out.println(Configuration.getBytes(key) / unit.getValue());
              }else{
                System.out.println(Configuration.getTime(arg, key));
              }
            } catch (IllegalArgumentException e) {
              printHelp(String.format("%s is not a valid unit", arg));
              return 1;
            }
          } else {
            System.out.println(Configuration.get(key));
          }
        }
        break;
      default:
        printHelp("More arguments than expected");
        return 1;
    }
    return 0;
  }