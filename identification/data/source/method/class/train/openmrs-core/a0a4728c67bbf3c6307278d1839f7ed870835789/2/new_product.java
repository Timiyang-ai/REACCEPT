public Set<Obs> getAllObs(boolean includeVoided) {
		if (includeVoided && obs != null) {
			return obs;
		}
		
		Set<Obs> ret = new LinkedHashSet<>();
		
		if (this.obs != null) {
			for (Obs o : this.obs) {
				if (includeVoided) {
					ret.add(o);
				} else if (!o.isVoided()) {
					ret.add(o);
				}
			}
		}
		return ret;
	}