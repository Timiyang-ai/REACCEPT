	@Test
	public void decode(){
		assertEquals(null, EvaluatorUtil.decode((Object)null));

		Computable value = new Computable(){

			@Override
			public String getResult(){
				return "value";
			}
		};

		assertEquals("value", EvaluatorUtil.decode(value));

		assertEquals(Arrays.asList("value"), EvaluatorUtil.decode(Arrays.asList(value)));
		assertEquals(Arrays.asList("value", "value"), EvaluatorUtil.decode(Arrays.asList(value, value)));

		Computable invalidValue = new Computable(){

			@Override
			public Object getResult(){
				throw new UnsupportedOperationException();
			}
		};

		try {
			EvaluatorUtil.decode(invalidValue);

			fail();
		} catch(UnsupportedOperationException uoe){
			// Ignored
		}
	}