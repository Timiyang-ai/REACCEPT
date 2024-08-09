public List<PersonListItem> findPeopleByRoles(String searchPhrase, boolean includeVoided, String roles) {
		Vector<PersonListItem> personList = new Vector<PersonListItem>();
		
		if (roles != null)
			roles = roles.trim();
		
		// if roles were given, search for users with those roles
		if (roles != null && roles.length() > 0) {
			UserService us = Context.getUserService();
			
			List<Role> roleList = new Vector<Role>();
			
			if (roles != null)
				if (roles.length() > 0) {
					String[] splitRoles = roles.split(",");
					for (String role : splitRoles) {
						roleList.add(new Role(role));
					}
				}
			
			for (User u : us.getUsers(searchPhrase, roleList, includeVoided)) {
				personList.add(new UserListItem(u));
			}
			
		} else {
			
			// if no roles were given, search for normal people
			PersonService ps = Context.getPersonService();
			for (Person p : ps.getPeople(searchPhrase, null)) {
				personList.add(PersonListItem.createBestMatch(p));
			}
			
			// also search on patient identifier if the query contains a number
			if (searchPhrase.matches(".*\\d+.*")) {
				PatientService patientService = Context.getPatientService();
				for (Patient p : patientService.getPatients(null, searchPhrase, null, false)) {
					personList.add(PersonListItem.createBestMatch(p));
				}
			}
			
		}
		
		return personList;
	}