@Override
	public ManagementVertex next() {

		if (traversalStack.isEmpty()) {

			if (numVisitedEntryVertices < 0) {
				// User chose a specific entry vertex
				return null;
			}

			TraversalEntry newentry;

			if (forward) {
				newentry = new TraversalEntry(managementGraph.getInputVertex(this.startStage, numVisitedEntryVertices),
					0, 0);
			} else {
				newentry = new TraversalEntry(
					managementGraph.getOutputVertex(this.startStage, numVisitedEntryVertices), 0, 0);
			}

			traversalStack.push(newentry);
		}

		final ManagementVertex returnVertex = traversalStack.peek().getManagementVertex();

		// Propose vertex to be visited next
		do {

			final TraversalEntry te = traversalStack.peek();

			// Check if we can traverse deeper into the graph
			final ManagementVertex candidateVertex = getCandidateVertex(te, forward);
			if (candidateVertex == null) {
				// Pop it from the stack
				traversalStack.pop();
			} else {
				// Create new entry and put it on the stack
				final TraversalEntry newte = new TraversalEntry(candidateVertex, 0, 0);
				traversalStack.add(newte);
				break;
			}

		} while (!traversalStack.isEmpty());

		// Mark vertex as already visited
		alreadyVisited.add(returnVertex);

		return returnVertex;
	}