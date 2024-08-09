public void addObs(Obs observation) {
		if (obs == null)
			obs = new HashSet<Obs>();
		
		if (observation != null) {
			obs.add(observation);
			
			//Propagate some attributes to the obs and any groupMembers
			
			// a Deque is a two-ended queue, that lets us add to the end, and fetch from the beginning
			Deque<Obs> obsToUpdate = new ArrayDeque<Obs>();
			obsToUpdate.add(observation);
			
			//prevent infinite recursion if an obs is its own group member
			Set<Obs> seenIt = new HashSet<Obs>();
			
			while (!obsToUpdate.isEmpty()) {
				Obs o = obsToUpdate.removeFirst();
				
				//has this obs already been processed?
				if (o == null || seenIt.contains(o))
					continue;
				seenIt.add(o);
				
				o.setEncounter(this);
				
				//if the attribute was already set, preserve it
				//if not, inherit the values sfrom the encounter
				if (o.getObsDatetime() == null)
					o.setObsDatetime(getEncounterDatetime());
				if (o.getPerson() == null)
					o.setPerson(getPatient());
				if (o.getLocation() == null)
					o.setLocation(getLocation());
				
				//propagate attributes to  all group members as well
				if (o.getGroupMembers(true) != null) {
					obsToUpdate.addAll(o.getGroupMembers());
				}
			}
			
		}
	}