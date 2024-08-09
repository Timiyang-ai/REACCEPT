public Set<Obs> getObsAtTopLevel(boolean includeVoided) {
	
		return getAllObs(includeVoided).stream()
				.filter(o -> o.getObsGroup() == null)
				.collect(Collectors.toSet());
	}