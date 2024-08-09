@Override
	public ManagementVertex next() {

		if (this.traversalStack.isEmpty()) {

			if (this.numVisitedEntryVertices < 0) {
				// User chose a specific entry vertex
				return null;
			}

			TraversalEntry newentry;

			if (this.forward) {
				newentry = new TraversalEntry(this.managementGraph.getInputVertex(this.startStage, this.numVisitedEntryVertices),
					0, 0);
			} else {
				newentry = new TraversalEntry(
					managementGraph.getOutputVertex(this.startStage, this.numVisitedEntryVertices), 0, 0);
			}

			this.traversalStack.push(newentry);
			this.alreadyVisited.add(newentry.getManagementVertex());
		}

		final ManagementVertex returnVertex = this.traversalStack.peek().getManagementVertex();

		// Propose vertex to be visited next
		do {

			final TraversalEntry te = this.traversalStack.peek();

			// Check if we can traverse deeper into the graph
			final ManagementVertex candidateVertex = getCandidateVertex(te, this.forward);
			if (candidateVertex == null) {
				// Pop it from the stack
				this.traversalStack.pop();
			} else {
				// Create new entry and put it on the stack
				final TraversalEntry newte = new TraversalEntry(candidateVertex, 0, 0);
				this.traversalStack.push(newte);
				this.alreadyVisited.add(candidateVertex);
				break;
			}

		} while (!this.traversalStack.isEmpty());

		return returnVertex;
	}