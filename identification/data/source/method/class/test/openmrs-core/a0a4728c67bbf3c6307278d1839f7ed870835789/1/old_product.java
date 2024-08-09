public Set<Obs> getAllObs(boolean includeVoided) {
		Set<Obs> ret = new HashSet<Obs>();
		if (this.obs != null) {
			for (Obs o : this.obs) {
				if (includeVoided)
					ret.add(o);
				else if (!o.isVoided())
					ret.add(o);
			}
		}
		return ret;
	}