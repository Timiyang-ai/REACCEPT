public List<TemplateNode> rewrite(SoyFileSetNode fileSet) throws SoyAutoescapeException {
    // Defensively copy so our loops below hold.
    List<SoyFileNode> files = ImmutableList.copyOf(fileSet.getChildren());

    Map<String, ImmutableList<TemplateNode>> templatesByName = findTemplates(files);

    // Inferences collects all the typing decisions we make, templates we derive, and escaping modes
    // we choose.
    Inferences inferences = new Inferences(
        soyEscapingDirectives, fileSet.getNodeIdGenerator(), templatesByName);
    Collection<TemplateNode> allTemplates = inferences.getAllTemplates();
    TemplateCallGraph callGraph = new TemplateCallGraph(templatesByName);
    // Generate a call graph, creating a dummy root that calls all non-private template in
    // Context.PCDATA, and then type the minimal ancestor set needed to reach all contextual
    // templates whether private or not.
    // This should have the effect of being a NOP when there are no contextual templates, will type
    // all contextual templates, and will not barf on private templates that might be declared
    // autoescape="false" because they do funky things that are provably safe by human reason but
    // not by this algorithm.
    Set<TemplateNode> templateNodesToType = callGraph.callersOf(
        Collections2.filter(allTemplates, IS_CONTEXTUAL));
    templateNodesToType.addAll(Collections2.filter(allTemplates, IS_PUBLIC_CONTEXTUAL));
    for (TemplateNode templateNode : templateNodesToType) {
      // TODO: In the future we may want to let authors specify a different starting context
      // via {template autoescape="contextual-js"} or a similar notation.  When that happens,
      // change Context.HTML_PCDATA below as appropriate, and the derived name code in
      // DerivedTemplates.
      InferenceEngine.inferTemplateEndContext(templateNode, Context.HTML_PCDATA, inferences);
    }

    // Store inferences so that after processing, clients can access the output contexts for
    // templates.
    this.inferences = inferences;

    // Now that we know we don't fail with exceptions, apply the changes to the given files.
    return new Rewriter(inferences, sanitizedContentOperators).rewrite(fileSet);
  }