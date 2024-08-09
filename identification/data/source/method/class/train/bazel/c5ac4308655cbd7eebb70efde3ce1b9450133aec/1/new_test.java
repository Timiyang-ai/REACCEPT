  public static List<String> canonicalize(Class<? extends OptionsBase> optionsClass, String... args)
      throws OptionsParsingException {

    OptionsParser parser =
        OptionsParser.builder().optionsClasses(optionsClass).allowResidue(false).build();
    parser.parse(Arrays.asList(args));
    return parser.canonicalize();
  }