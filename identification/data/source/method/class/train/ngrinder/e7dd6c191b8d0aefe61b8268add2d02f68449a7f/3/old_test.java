	@Test
	public void runScriptTest() {
		Map<String, Object> result;

		Map<String, Object> param = buildMap("script", "");
		result = scriptController.run(param);
		assertThat((String) result.get("result"), isEmptyString());
		param.put("script", "print \'hello\'");
		result = scriptController.run(param);
		assertThat(result.get("result"), notNullValue());
		assertThat((String) result.get("result"), containsString("hello"));

		param.put("script", "int a = 1");
		scriptController.run(param);
		param.put("script", "print a");
		result = scriptController.run(param);
		assertThat((String) result.get("result"), containsString("No such property"));
	}