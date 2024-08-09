public Set<Obs> getAllFlattenObs(boolean includeVoided) {

		Set<Obs> ret = new LinkedHashSet<>();

		if (this.obs != null) {
			for (Obs o : this.obs) {
				if(!o.getVoided()) {
					ret.add(o);
					ret.addAll(getObsLeaves(o));
				}
			}
		}
		return ret;
	}