public void addObs(Obs observation) {
		if (observation.getPerson() == null)
			observation.setPerson(this.getPatient());
		if (observation.getLocation() == null)
			observation.setLocation(this.getLocation());
		if (observation.getObsDatetime() == null)
			observation.setObsDatetime(this.getEncounterDatetime());
		if (obs == null)
			obs = new HashSet<Obs>();
		if (observation != null) {
			observation.setEncounter(this);
			
			if (observation.getObsDatetime() == null)
				observation.setObsDatetime(getEncounterDatetime());
			if (observation.getPerson() == null)
				observation.setPerson(getPatient());
			if (observation.getLocation() == null)
				observation.setLocation(getLocation());
			obs.add(observation);
		}
	}