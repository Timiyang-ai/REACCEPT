public void rewrite(SoyFileSetNode fileSet, ErrorReporter errorReporter) {
    ImmutableListMultimap<String, TemplateNode> templatesByName =
        findTemplates(fileSet.getChildren());

    // Inferences collects all the typing decisions we make and escaping modes we choose.
    Inferences inferences = new Inferences(templatesByName);

    for (SoyFileNode file : fileSet.getChildren()) {
      if (file.getSoyFileKind() != SoyFileKind.SRC) {
        continue; // we don't need to inspect non SRC files
      }
      for (TemplateNode templateNode : file.getChildren()) {
        try {
          // In strict mode, the author specifies the kind of SanitizedContent to produce, and
          // thus the context in which to escape.  In deprecated-contextual, it is always HTML.
          Context startContext =
              Context.getStartContextForContentKind(
                  MoreObjects.firstNonNull(
                      templateNode.getContentKind(), SanitizedContentKind.HTML));
          InferenceEngine.inferTemplateEndContext(
              templateNode, startContext, inferences, errorReporter);
        } catch (SoyAutoescapeException e) {
          reportError(errorReporter, e);
        }
      }
    }
    if (errorReporter.hasErrors()) {
      return;
    }
    // Now that we know we don't fail with exceptions, apply the changes to the given files.
    new Rewriter(inferences, fileSet.getNodeIdGenerator(), printDirectives).rewrite(fileSet);
  }