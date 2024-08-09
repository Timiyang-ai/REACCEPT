public Set<Obs> getAllFlattenedObs(boolean includeVoided) {

		Set<Obs> ret = new LinkedHashSet<>();

		if (this.obs != null) {
			for (Obs o : this.obs) {
				if (includeVoided || (!o.getVoided())) {
					ret.addAll(getFlattenedObsLeaves(o, includeVoided));
				}
			}
		}
		return ret;
	}