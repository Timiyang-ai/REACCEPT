public Set<Obs> getAllFlattenObs(boolean includeVoided) {

		Set<Obs> ret = new LinkedHashSet<>();

		if (this.obs != null) {
			for (Obs o : this.obs) {
				if (includeVoided || (!includeVoided && !o.getVoided())) {
					ret.add(o);
					ret.addAll(getFlattenObsLeaves(o, includeVoided));
				}
			}
		}
		return ret;
	}