@Test
	public void testCompletion_object() throws Exception{
		ICompilationUnit unit = getWorkingCopy(
				"src/java/Foo.java",
				"public class Foo {\n"+
						"	void foo() {\n"+
						"		Objec\n"+
						"	}\n"+
				"}\n");
		int[] loc = findCompletionLocation(unit, "Objec");
		CompletionList list = server.completion(JsonMessageHelper.getParams(createCompletionRequest(unit, loc[0], loc[1]))).join();
		assertNotNull(list);
		assertFalse("No proposals were found",list.getItems().isEmpty());
		for ( CompletionItem item : list.getItems()) {
			assertTrue(isNotBlank(item.getLabel()));
			assertNotNull(item.getKind() );
			assertTrue(isNotBlank(item.getSortText()));
			//text edits are not set during calls to "completion"
			assertNull(item.getTextEdit());
			assertNull(item.getInsertText());
			//Check contains data used for completionItem resolution
			@SuppressWarnings("unchecked")
			Map<String,String> data = (Map<String, String>) item.getData();
			assertNotNull(data);
			assertTrue(isNotBlank(data.get(CompletionResolveHandler.DATA_FIELD_URI)));
			assertTrue(isNotBlank(data.get(CompletionResolveHandler.DATA_FIELD_PROPOSAL_ID)));
			assertTrue(isNotBlank(data.get(CompletionResolveHandler.DATA_FIELD_REQUEST_ID)));
		}
	}