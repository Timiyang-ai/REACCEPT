	@Test
	public void getPersonName_shouldGetNotvoidedPersonNameIfPreferredAddressDoesNotExist() {
		
		PersonName notVoidedName = PersonNameBuilder.newBuilder().withVoided(false).build();
		PersonName voidedName = PersonNameBuilder.newBuilder().withVoided(true).build();

		checkGetPersonNameResultForVoidedPerson(notVoidedName, new HashSet<>(Arrays.asList(notVoidedName,
				voidedName)));
	}