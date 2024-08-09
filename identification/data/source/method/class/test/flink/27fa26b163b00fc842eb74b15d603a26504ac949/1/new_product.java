@Override
	public boolean hasNext() {

		if (this.traversalStack.isEmpty()) {

			if (this.numVisitedEntryVertices < 0) {
				// User chose a specific starting vertex
				return false;
			}

			++this.numVisitedEntryVertices;

			if (this.forward) {
				if (this.managementGraph.getNumberOfInputVertices(this.startStage) <= this.numVisitedEntryVertices) {
					return false;
				}
			} else {
				if (this.managementGraph.getNumberOfOutputVertices(this.startStage) <= this.numVisitedEntryVertices) {
					return false;
				}
			}
		}

		return true;
	}