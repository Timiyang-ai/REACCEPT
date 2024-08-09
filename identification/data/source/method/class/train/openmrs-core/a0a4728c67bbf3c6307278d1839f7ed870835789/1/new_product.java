public Set<Obs> getAllObs(boolean includeVoided) {
		if (includeVoided && obs != null) {
			return obs;
		}
		
		Set<Obs> ret = new LinkedHashSet<>();
		
		if (this.obs != null) {
			ret = this.obs.stream().
					filter(o -> includeVoided || !o.getVoided())
					.collect(Collectors.toSet());
		}
		return ret;
	}