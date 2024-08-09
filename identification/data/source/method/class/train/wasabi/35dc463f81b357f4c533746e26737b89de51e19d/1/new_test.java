    @Test(expected = RepositoryException.class)
    public void getEmptyBucketsTest() {
        Experiment.ID experimentId = Experiment.ID.newInstance();
        List<Map> input = new ArrayList<Map>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("label", "TEST_LABEL");
        input.add(map);
        when(transaction.select(anyString(), Matchers.anyVararg())).thenReturn(input);
        Map<Bucket.Label, BucketCounts> result = databaseAnalytics.getEmptyBuckets(experimentId);
        assertThat(result.size(), is(1));
        assertThat(result.get(Bucket.Label.valueOf("TEST_LABEL")).getLabel().toString(), is("TEST_LABEL"));
        assertThat(result.get(Bucket.Label.valueOf("TEST_LABEL")).getActionCounts().size(), is(0));
        //exception while select
        doThrow(new RuntimeException()).when(transaction)
                .select(anyString(), Matchers.anyVararg());
        databaseAnalytics.getEmptyBuckets(experimentId);
        fail();
    }