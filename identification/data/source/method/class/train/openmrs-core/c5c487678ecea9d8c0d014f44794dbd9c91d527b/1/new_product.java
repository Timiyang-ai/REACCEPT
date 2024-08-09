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
		
		int ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyName() != null ? name1.getFamilyName().toLowerCase()
		        : null, name2.getFamilyName() != null ? name2.getFamilyName().toLowerCase() : null);
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyName2() != null ? name1.getFamilyName().toLowerCase()
			        : null, name2.getFamilyName2() != null ? name2.getFamilyName2().toLowerCase() : null);
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getGivenName() != null ? name1.getGivenName().toLowerCase()
			        : null, name2.getGivenName() != null ? name2.getGivenName().toLowerCase() : null);
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getMiddleName() != null ? name1.getMiddleName().toLowerCase()
			        : null, name2.getMiddleName() != null ? name2.getMiddleName().toLowerCase() : null);
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyNamePrefix() != null ? name1.getFamilyNamePrefix()
			        .toLowerCase() : null, name2.getFamilyNamePrefix() != null ? name2.getFamilyNamePrefix().toLowerCase()
			        : null);
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyNameSuffix() != null ? name1.getFamilyNameSuffix()
			        .toLowerCase() : null, name2.getFamilyNameSuffix() != null ? name2.getFamilyNameSuffix().toLowerCase()
			        : null);
		}
		
		return ret;
	}