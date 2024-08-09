public PersonName parsePersonName(String name) throws APIException {
		String firstName = name;
		String middleName = "";
		String lastName = "";
		
		if (name.contains(",")) {
			String[] names = name.split(", ");
			String[] firstNames = names[1].split(" ");
			if (firstNames.length == 2) {
				lastName = names[0];
				firstName = firstNames[0];
				middleName = firstNames[1];
			} else {
				firstName = names[1];
				lastName = names[0];
			}
		} else if (name.contains(" ")) {
			String[] names = name.split(" ");
			if (names.length == 3) {
				firstName = names[0];
				middleName = names[1];
				lastName = names[2];
			} else {
				firstName = names[0];
				lastName = names[1];
			}
		}
		
		return new PersonName(firstName, middleName, lastName);
	}