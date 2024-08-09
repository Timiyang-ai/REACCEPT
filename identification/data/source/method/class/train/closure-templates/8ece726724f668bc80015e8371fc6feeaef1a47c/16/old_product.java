public List<TemplateNode> rewrite(SoyFileSetNode fileSet) {
    // Do preliminary sanity checks.
    new CheckEscapingSanityVisitor(errorReporter).exec(fileSet);

    // Defensively copy so our loops below hold.
    List<SoyFileNode> files = ImmutableList.copyOf(fileSet.getChildren());

    Map<String, ImmutableList<TemplateNode>> templatesByName = findTemplates(files);

    // Inferences collects all the typing decisions we make, templates we derive, and escaping modes
    // we choose.
    Inferences inferences = new Inferences(
        autoescapeCancellingDirectives, fileSet.getNodeIdGenerator(), templatesByName);
    ImmutableList.Builder<SlicedRawTextNode> slicedRawTextNodesBuilder = ImmutableList.builder();

    Collection<TemplateNode> allTemplates = inferences.getAllTemplates();
    TemplateCallGraph callGraph = new TemplateCallGraph(templatesByName, errorReporter);
    // Generate a call graph, creating a dummy root that calls all non-private template in
    // Context.PCDATA, and then type the minimal ancestor set needed to reach all contextual
    // templates whether private or not.
    // This should have the effect of being a NOP when there are no contextual templates, will type
    // all contextual templates, and will not barf on private templates that might be declared
    // autoescape="false" because they do funky things that are provably safe by human reason but
    // not by this algorithm.
    Set<TemplateNode> templateNodesToType = callGraph.callersOf(
        Collections2.filter(allTemplates, IS_CONTEXTUAL));
    templateNodesToType.addAll(Collections2.filter(allTemplates, REQUIRES_INFERENCE));
    Set<SourceLocation> errorLocations = new HashSet<>();
    for (TemplateNode templateNode : templateNodesToType) {
      try {
        // In strict mode, the author specifies the kind of SanitizedContent to produce, and thus
        // the context in which to escape.
        Context startContext = (templateNode.getContentKind() != null) ?
            Context.getStartContextForContentKind(templateNode.getContentKind()) :
            Context.HTML_PCDATA;
        InferenceEngine.inferTemplateEndContext(
            templateNode,
            startContext,
            inferences,
            autoescapeCancellingDirectives,
            slicedRawTextNodesBuilder,
            errorReporter);
      } catch (SoyAutoescapeException e) {
        reportError(errorLocations, e);
      }
    }

    if (!errorLocations.isEmpty()) {
      // Bail out early, since future passes won't succeed and may throw precondition errors.
      return ImmutableList.<TemplateNode>of();
    }

    // Store inferences so that after processing, clients can access the output contexts for
    // templates.
    this.inferences = inferences;

    // Store context boundaries so that later passes can make use of element/attribute boundaries.
    this.slicedRawTextNodes = slicedRawTextNodesBuilder.build();

    runVisitorOnAllTemplatesIncludingNewOnes(
        inferences, new NonContextualTypedRenderUnitNodesVisitor());

    // Now that we know we don't fail with exceptions, apply the changes to the given files.
    List<TemplateNode> extraTemplates = new Rewriter(
        inferences, sanitizedContentOperators, errorReporter).rewrite(fileSet);

    runVisitorOnAllTemplatesIncludingNewOnes(inferences,
        new PerformDeprecatedNonContextualAutoescapeVisitor(
            autoescapeCancellingDirectives, errorReporter, fileSet.getNodeIdGenerator()));

    return extraTemplates;
  }