public PersonName parsePersonName(String name) throws APIException {
		name = name.trim(); // strip beginning/ending whitespace
		
		String firstName = name;
		String middleName = "";
		String lastName = "";
		
		if (name.contains(",")) {
			
			// trim off all trailing commas
			while (name.endsWith(","))
				name = name.substring(0, name.length() - 1);
			
			String[] names = name.split(", ");
			if (names.length > 1) {
				String[] firstNames = names[1].split(" ");
				if (firstNames.length == 2) {
					// user entered "Smith, John Adam"
					lastName = names[0];
					firstName = firstNames[0];
					middleName = firstNames[1];
				} else {
					// user entered "Smith, John"
					firstName = names[1];
					lastName = names[0];
				}
			}
			else {
				// user entered something with a trailing comma
				firstName = name;
			}
		} else if (name.contains(" ")) {
			String[] names = name.split(" ");
			if (names.length == 3) {
				// user entered "John Adam Smith"
				firstName = names[0];
				middleName = names[1];
				lastName = names[2];
			} else {
				// user entered "John Smith"
				firstName = names[0];
				lastName = names[1];
			}
		}
		
		return new PersonName(firstName, middleName, lastName);
	}