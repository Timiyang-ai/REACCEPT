@Test
	public void liftM2(){
		Streamable<String> stream1 = Streamable.of("ALL UPPER","MiXed Case");
		Streamable<String> stream2 = Streamable.of("MixedCase","all lower");
		
		
		AnyM<String> responses = LiftMFunctions.liftM2(this::response).apply(anyM(stream1), anyM(stream2));
		
		assertThat(responses.toList(),equalTo(Arrays.asList("all upper::MIXEDCASE", 
				"all upper::ALL LOWER", "mixed case::MIXEDCASE", "mixed case::ALL LOWER")));
		
	}