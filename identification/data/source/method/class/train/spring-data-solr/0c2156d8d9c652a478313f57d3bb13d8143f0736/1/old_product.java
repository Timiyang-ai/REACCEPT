@Override
	public <T> Collection<T> getById(final Collection<? extends Serializable> ids, final Class<T> clazz) {

		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}

		return execute(new SolrCallback<Collection<T>>() {
			@Override
			public Collection<T> doInSolr(SolrClient solrClient) throws SolrServerException, IOException {

				QueryResponse response = new SolrRealtimeGetRequest(ids).process(solrClient,
						getSolrCoreOrBeanCollection(clazz));
				return convertSolrDocumentListToBeans(response.getResults(), clazz);
			}

		});
	}