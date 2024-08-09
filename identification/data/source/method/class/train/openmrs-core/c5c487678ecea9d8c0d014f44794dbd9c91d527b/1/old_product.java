public static int comparePersonsByName(Person person1, Person person2) {
		
		// test for null cases (sorting them to be last in a list)
		if (person1 == null || person1.getPersonName() == null) {
			return 1;
		} else if (person2 == null || person2.getPersonName() == null) {
			return -1;
		}
		
		// if neither are null, do the actual comparison
		PersonName name1 = person1.getPersonName();
		PersonName name2 = person2.getPersonName();
		
		int ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyName(), name2.getFamilyName());
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyName2(), name2.getFamilyName2());
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getGivenName(), name2.getGivenName());
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getMiddleName(), name2.getMiddleName());
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyNamePrefix(), name2.getFamilyNamePrefix());
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyNameSuffix(), name2.getFamilyNameSuffix());
		}
		
		return ret;
	}