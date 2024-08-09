@Test
	public void testGetById() throws SolrServerException, IOException {

		ArgumentCaptor<SolrRequest> captor = ArgumentCaptor.forClass(SolrRequest.class);
		QueryResponse responseMock = Mockito.mock(QueryResponse.class);
		SolrDocumentList resultList = new SolrDocumentList();
		Mockito.when(responseMock.getResults()).thenReturn(resultList);
		Mockito.when(solrClientMock.request(captor.capture(), Matchers.anyString())).thenReturn(new NamedList<Object>());

		DocumentWithIndexAnnotations result = solrTemplate.getById("myId", DocumentWithIndexAnnotations.class);

		Mockito.verify(solrClientMock, Mockito.times(1)).request(captor.capture(), Matchers.anyString());
		Assert.assertNull(result);
		Assert.assertEquals("myId", captor.getValue().getParams().get("ids"));
		Assert.assertEquals("/get", captor.getValue().getPath());
	}