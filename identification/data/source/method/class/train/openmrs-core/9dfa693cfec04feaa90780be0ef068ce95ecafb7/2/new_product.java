public Set<Obs> getObs() {
		Set<Obs> ret = new HashSet<Obs>();
		
		if (this.obs != null) {
			for (Obs o : this.obs)
				ret.addAll(getObsLeaves(o));
				// this should be all thats needed unless the encounter has been built by hand
				//if (o.isVoided() == false && o.isObsGrouping() == false)
				//	ret.add(o);
		}
		
			
		return ret;
	}