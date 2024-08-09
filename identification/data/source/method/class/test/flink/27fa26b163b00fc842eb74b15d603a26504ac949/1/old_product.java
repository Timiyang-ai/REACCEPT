@Override
	public boolean hasNext() {

		if (traversalStack.isEmpty()) {

			if (numVisitedEntryVertices < 0) {
				// User chose a specific starting vertex
				return false;
			}

			numVisitedEntryVertices++;

			if (forward) {
				if (managementGraph.getNumberOfInputVertices(this.startStage) <= numVisitedEntryVertices) {
					return false;
				}
			} else {
				if (managementGraph.getNumberOfOutputVertices(this.startStage) <= numVisitedEntryVertices) {
					return false;
				}
			}
		}

		return true;
	}