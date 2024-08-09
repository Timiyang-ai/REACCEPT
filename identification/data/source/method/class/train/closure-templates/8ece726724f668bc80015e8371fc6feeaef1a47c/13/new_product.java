public List<TemplateNode> rewrite(
      SoyFileSetNode fileSet, TemplateRegistry registry, ErrorReporter errorReporter) {
    // Defensively copy so our loops below hold.
    List<SoyFileNode> files = ImmutableList.copyOf(fileSet.getChildren());

    // TODO(lukes): why aren't we just using the TemplateRegistry?
    Map<String, ImmutableList<TemplateNode>> templatesByName = findTemplates(files);

    // Inferences collects all the typing decisions we make, templates we derive, and escaping modes
    // we choose.
    Inferences inferences = new Inferences(fileSet.getNodeIdGenerator(), templatesByName);

    Collection<TemplateNode> allTemplates = inferences.getAllTemplates();
    TemplateCallGraph callGraph = new TemplateCallGraph(templatesByName);
    // Generate a call graph, creating a dummy root that calls all non-private template in
    // Context.PCDATA, and then type the minimal ancestor set needed to reach all contextual
    // templates whether private or not.
    // This should have the effect of being a NOP when there are no contextual templates, will type
    // all contextual templates, and will not barf on private templates that might be declared
    // autoescape="false" because they do funky things that are provably safe by human reason but
    // not by this algorithm.
    Collection<TemplateNode> thatRequireInference =
        Collections2.filter(allTemplates, REQUIRES_INFERENCE);
    Set<TemplateNode> templateNodesToType = callGraph.callersOf(thatRequireInference);
    templateNodesToType.addAll(thatRequireInference);

    Set<SourceLocation> errorLocations = new HashSet<>();
    for (TemplateNode templateNode : templateNodesToType) {
      try {
        // In strict mode, the author specifies the kind of SanitizedContent to produce, and thus
        // the context in which to escape.
        Context startContext =
            (templateNode.getContentKind() != null)
                ? Context.getStartContextForContentKind(templateNode.getContentKind())
                : Context.HTML_PCDATA;
        InferenceEngine.inferTemplateEndContext(
            templateNode, startContext, inferences, errorReporter);
      } catch (SoyAutoescapeException e) {
        reportError(errorReporter, errorLocations, e);
      }
    }

    if (!errorLocations.isEmpty()) {
      // Bail out early, since future passes won't succeed and may throw precondition errors.
      return ImmutableList.<TemplateNode>of();
    }

    // Store inferences so that after processing, clients can access the output contexts for
    // templates.
    this.inferences = inferences;

    runVisitorOnAllTemplatesIncludingNewOnes(
        inferences, new NonContextualTypedRenderUnitNodesVisitor(errorReporter));

    // Now that we know we don't fail with exceptions, apply the changes to the given files.
    List<TemplateNode> extraTemplates = new Rewriter(inferences, printDirectives).rewrite(fileSet);

    runVisitorOnAllTemplatesIncludingNewOnes(
        inferences,
        new PerformDeprecatedNonContextualAutoescapeVisitor(fileSet.getNodeIdGenerator()));

    return extraTemplates;
  }