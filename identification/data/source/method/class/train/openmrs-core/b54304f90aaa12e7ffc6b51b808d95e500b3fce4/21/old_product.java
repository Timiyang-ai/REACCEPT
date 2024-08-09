public Set<Obs> getObsAtTopLevel(boolean includeVoided) {
		Set<Obs> ret = new HashSet<Obs>();
		for (Obs o : getAllObs(includeVoided)) {
			if (o.getObsGroup() == null) {
				ret.add(o);
			}
		}
		return ret;
	}