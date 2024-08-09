public void addObs(Obs observation) {
		observation.setEncounter(this);
		if (obs == null)
			obs = new HashSet<Obs>();
		if (observation != null)
			obs.add(observation);
	}