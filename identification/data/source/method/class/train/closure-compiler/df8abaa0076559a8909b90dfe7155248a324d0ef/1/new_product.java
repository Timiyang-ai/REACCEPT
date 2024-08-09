private void parse(List<String> args) throws CmdLineException {
      parser.parseArgument(args.toArray(new String[] {}));

      compilationLevelParsed = CompilationLevel.fromString(Ascii.toUpperCase(compilationLevel));
      if (compilationLevelParsed == null) {
        throw new CmdLineException(
            parser, "Bad value for --compilation_level: " + compilationLevel);
      }
    }