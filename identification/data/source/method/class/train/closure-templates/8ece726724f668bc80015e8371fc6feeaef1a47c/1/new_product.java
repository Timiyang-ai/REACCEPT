public void rewrite(
      ImmutableList<SoyFileNode> sourceFiles, IdGenerator idGenerator, TemplateRegistry registry) {

    // Inferences collects all the typing decisions we make and escaping modes we choose.
    Inferences inferences = new Inferences(registry);

    // TODO(lukes): having a separation of inference and rewriting was important when we did
    // template cloning and derivation.  Now that that is deleted we could combine these into a
    // single pass over the tree and delete the Inferences class which may simplify things.
    for (SoyFileNode file : sourceFiles) {
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
    Rewriter rewriter = new Rewriter(inferences, idGenerator, printDirectives);
    for (SoyFileNode file : sourceFiles) {
      rewriter.rewrite(file);
    }
  }