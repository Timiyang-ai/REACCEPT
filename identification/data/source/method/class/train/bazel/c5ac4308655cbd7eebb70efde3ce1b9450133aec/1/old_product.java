public static List<String> canonicalize(
      Collection<Class<? extends OptionsBase>> optionsClasses, List<String> args)
      throws OptionsParsingException {
    OptionsParser parser = new OptionsParser(optionsClasses);
    parser.setAllowResidue(false);
    parser.parse(args);
    return parser.impl.asCanonicalizedList();
  }