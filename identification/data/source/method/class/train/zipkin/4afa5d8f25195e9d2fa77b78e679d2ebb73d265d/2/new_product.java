public SpanNode build(List<Span> spans) {
      if (spans.isEmpty()) throw new IllegalArgumentException("spans were empty");
      clear();

      // In order to make a tree, we need clean data. This will merge any duplicates so that we
      // don't have redundant leaves on the tree.
      List<Span> cleaned = Trace.merge(spans);
      int length = cleaned.size();
      String traceId = cleaned.get(0).traceId();

      if (logger.isLoggable(FINE)) logger.fine("building trace tree: traceId=" + traceId);

      // Next, index all the spans so that we can understand any relationships.
      for (int i = 0; i < length; i++) {
        index(cleaned.get(i));
      }

      // Now that we've index references to all spans, we can revise any parent-child relationships.
      // Notably, by now, we can tell which is the root-most.
      for (int i = 0; i < length; i++) {
        process(cleaned.get(i));
      }

      // If we haven't found any root span, we can still make a tree using a synthetic node.
      if (rootSpan == null) {
        if (logger.isLoggable(FINE)) {
          logger.fine("substituting dummy node for missing root span: traceId=" + traceId);
        }
        rootSpan = new SpanNode(null);
      }

      // At this point, we have the most reliable parent-child relationships and can allocate spans
      // corresponding the the best place in the trace tree.
      for (Map.Entry<Object, Object> entry : spanToParent.entrySet()) {
        SpanNode child = keyToNode.get(entry.getKey());
        SpanNode parent = keyToNode.get(entry.getValue());

        if (parent == null) { // Handle headless by attaching spans missing parents to root
          rootSpan.addChild(child);
        } else {
          parent.addChild(child);
        }
      }
      return rootSpan;
    }