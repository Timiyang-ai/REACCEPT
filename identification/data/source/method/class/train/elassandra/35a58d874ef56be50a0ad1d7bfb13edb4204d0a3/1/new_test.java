@Test
    public void script_SingleValue() throws Exception {
        SearchResponse response = client().prepareSearch("idx")
                .addAggregation(dateHistogram("histo").script(new Script("doc['date'].value")).interval(DateHistogramInterval.MONTH))
                .execute().actionGet();

        assertSearchResponse(response);

        Histogram histo = response.getAggregations().get("histo");
        assertThat(histo, notNullValue());
        assertThat(histo.getName(), equalTo("histo"));
        List<? extends Bucket> buckets = histo.getBuckets();
        assertThat(buckets.size(), equalTo(3));

        DateTime key = new DateTime(2012, 1, 1, 0, 0, DateTimeZone.UTC);
        Histogram.Bucket bucket = buckets.get(0);
        assertThat(bucket, notNullValue());
        assertThat(bucket.getKeyAsString(), equalTo(getBucketKeyAsString(key)));
        assertThat(((DateTime) bucket.getKey()), equalTo(key));
        assertThat(bucket.getDocCount(), equalTo(1l));

        key = new DateTime(2012, 2, 1, 0, 0, DateTimeZone.UTC);
        bucket = buckets.get(1);
        assertThat(bucket, notNullValue());
        assertThat(bucket.getKeyAsString(), equalTo(getBucketKeyAsString(key)));
        assertThat(((DateTime) bucket.getKey()), equalTo(key));
        assertThat(bucket.getDocCount(), equalTo(2l));

        key = new DateTime(2012, 3, 1, 0, 0, DateTimeZone.UTC);
        bucket = buckets.get(2);
        assertThat(bucket, notNullValue());
        assertThat(bucket.getKeyAsString(), equalTo(getBucketKeyAsString(key)));
        assertThat(((DateTime) bucket.getKey()), equalTo(key));
        assertThat(bucket.getDocCount(), equalTo(3l));
    }