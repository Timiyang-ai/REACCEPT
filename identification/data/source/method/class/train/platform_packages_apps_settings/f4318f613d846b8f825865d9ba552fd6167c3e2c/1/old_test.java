    @Test
    public void extractUidFromStatsDimensionsValue_extractCorrectUid() {
        // Build an integer dimensions value.
        final StatsDimensionsValue intValue = mock(StatsDimensionsValue.class);
        when(intValue.isValueType(INT_VALUE_TYPE)).thenReturn(true);
        when(intValue.getField()).thenReturn(AnomalyDetectionJobService.STATSD_UID_FILED);
        when(intValue.getIntValue()).thenReturn(UID);

        // Build a tuple dimensions value and put the previous integer dimensions value inside.
        final StatsDimensionsValue tupleValue = mock(StatsDimensionsValue.class);
        when(tupleValue.isValueType(TUPLE_VALUE_TYPE)).thenReturn(true);
        final List<StatsDimensionsValue> statsDimensionsValues = new ArrayList<>();
        statsDimensionsValues.add(intValue);
        when(tupleValue.getTupleValueList()).thenReturn(statsDimensionsValues);

        assertThat(mAnomalyDetectionJobService.extractUidFromStatsDimensionsValue(
                tupleValue)).isEqualTo(UID);
    }