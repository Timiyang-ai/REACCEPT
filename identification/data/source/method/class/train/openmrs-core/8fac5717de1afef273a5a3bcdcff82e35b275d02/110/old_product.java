@Transactional(readOnly=true)
	@Authorized({"View People"})
	public Set<Person> getSimilarPeople(String name, Integer birthyear,
			String gender) throws APIException;