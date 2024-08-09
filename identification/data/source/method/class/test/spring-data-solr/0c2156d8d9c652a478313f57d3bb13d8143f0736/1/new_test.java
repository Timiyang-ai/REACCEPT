@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testGetById() throws SolrServerException, IOException {

		ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);

		solrTemplate.getById("myId", DocumentWithIndexAnnotations.class);

		Mockito.verify(solrClientMock, Mockito.times(1)).getById(eq("core1"), captor.capture());
		Assert.assertThat((List<String>) captor.getValue(), IsCollectionContaining.hasItems("myId"));
	}