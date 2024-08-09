  public SoyFileNode rewrite(String... inputs) {
    ErrorReporter reporter = ErrorReporter.createForTest();
    SoyFileSetNode soyTree =
        SoyFileSetParserBuilder.forFileContents(inputs)
            .errorReporter(reporter)
            .allowUnboundGlobals(true)
            .addPrintDirectives(SOY_PRINT_DIRECTIVES)
            .runAutoescaper(true)
            .parse()
            .fileSet();

    if (!reporter.getErrors().isEmpty()) {
      SoyError soyError = reporter.getErrors().get(0);
      String message = soyError.message();
      if (message.startsWith(ContextualAutoescaper.AUTOESCAPE_ERROR_PREFIX)) {
        // Grab the part after the prefix (and the "- " used for indentation).
        message = message.substring(ContextualAutoescaper.AUTOESCAPE_ERROR_PREFIX.length() + 2);
        // Re-throw as an exception, so that tests are easier to write. I considered having the
        // tests explicitly check the error messages; however, there's a substantial risk that some
        // positive test might forget to check the error messages, and it leaves all callers of
        // this with two things to check.
        // TODO(gboyer): Once 100% of the contextual autoescaper's errors are migrated to the error
        // reporter, we can stop throwing and simply add explicit checks in the cases.
        throw new RewriteError(soyError, message);
      } else {
        throw new IllegalStateException("Unexpected error: " + message);
      }
    }
    return soyTree.getChild(0);
  }