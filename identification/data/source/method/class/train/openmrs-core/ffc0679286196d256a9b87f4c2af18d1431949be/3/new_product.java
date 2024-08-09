public static Obs newInstance(Obs obsToCopy) {
		Obs newObs = new Obs(obsToCopy.getPerson(), obsToCopy.getConcept(),
		                     obsToCopy.getObsDatetime(), obsToCopy.getLocation());

		newObs.setObsGroup(obsToCopy.getObsGroup());
		newObs.setAccessionNumber(obsToCopy.getAccessionNumber());
		newObs.setValueCoded(obsToCopy.getValueCoded());
		newObs.setValueDrug(obsToCopy.getValueDrug());
		newObs.setValueGroupId(obsToCopy.getValueGroupId());
		newObs.setValueDatetime(obsToCopy.getValueDatetime());
		newObs.setValueNumeric(obsToCopy.getValueNumeric());
		newObs.setValueStructuredNumeric(obsToCopy.getValueStructuredNumeric());
		newObs.setValueText(obsToCopy.getValueText());
		newObs.setComment(obsToCopy.getComment());
		newObs.setOrder(obsToCopy.getOrder());
		newObs.setEncounter(obsToCopy.getEncounter());
		newObs.setDateStarted(obsToCopy.getDateStarted());
		newObs.setDateStopped(obsToCopy.getDateStopped());
		newObs.setCreator(obsToCopy.getCreator());
		newObs.setDateCreated(obsToCopy.getDateCreated());
		newObs.setVoided(obsToCopy.getVoided());
		newObs.setVoidedBy(obsToCopy.getVoidedBy());
		newObs.setDateVoided(obsToCopy.getDateVoided());
		newObs.setVoidReason(obsToCopy.getVoidReason());

		if (obsToCopy.getGroupMembers() != null)
			for (Obs member : obsToCopy.getGroupMembers()) {
				// if the obs hasn't been saved yet, no need to duplicate it
				if (member.getObsId() == null)
					newObs.addGroupMember(member);
				else
					newObs.addGroupMember(Obs.newInstance(member));
			}

		return newObs;
	}