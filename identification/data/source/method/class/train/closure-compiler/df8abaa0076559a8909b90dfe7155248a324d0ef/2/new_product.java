private void parse(List<String> args) throws CmdLineException {
      CmdLineParser parser = new CmdLineParser(this);
      parser.parseArgument(args.toArray(new String[] {}));

      compilationLevelParsed =
          COMPILATION_LEVEL_MAP.get(compilationLevel.toUpperCase());
      if (compilationLevelParsed == null) {
        throw new CmdLineException(
            parser, "Bad value for --compilation_level: " + compilationLevel);
      }
    }