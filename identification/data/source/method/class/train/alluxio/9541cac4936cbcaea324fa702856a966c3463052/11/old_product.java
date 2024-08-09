public static int getConf(String... args) {
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd;

    try {
      cmd = parser.parse(OPTIONS, args, true /* stopAtNonOption */);
    } catch (ParseException e) {
      printHelp("Unable to parse input args: " + e.getMessage());
      return 1;
    }
    args = cmd.getArgs();
    StringBuilder output = new StringBuilder();
    switch (args.length) {
      case 0:
        for (Entry<String, String> entry : Configuration.toMap().entrySet()) {
          String key = entry.getKey();
          String value = entry.getValue();
          output.append(String.format("%s=%s", key, value));
          if (cmd.hasOption(SOURCE_OPTION_NAME)) {
            output.append(
                String.format(" (%s)", Configuration.getSource(PropertyKey.fromString(key))));
          }
          output.append("\n");
        }
        System.out.print(output.toString());
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
          if (cmd.hasOption(SOURCE_OPTION_NAME)) {
            System.out.println(Configuration.getSource(key));
          } else if (cmd.hasOption(UNIT_OPTION_NAME)) {
            String arg = cmd.getOptionValue(UNIT_OPTION_NAME).toUpperCase();
            try {
              ByteUnit byteUnit;
              byteUnit = ByteUnit.valueOf(arg);
              System.out.println(Configuration.getBytes(key) / byteUnit.getValue());
              break;
            } catch (Exception e) {
              // try next unit parse
            }
            try {
              TimeUnit timeUnit;
              timeUnit = TimeUnit.valueOf(arg);
              System.out.println(Configuration.getMs(key) / timeUnit.getValue());
              break;
            } catch (IllegalArgumentException ex) {
              // try next unit parse
            }
            printHelp(String.format("%s is not a valid unit", arg));
            return 1;
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