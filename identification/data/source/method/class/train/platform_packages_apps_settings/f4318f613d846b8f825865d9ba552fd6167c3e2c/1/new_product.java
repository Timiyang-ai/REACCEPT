@VisibleForTesting
    int extractUidFromStatsDimensionsValue(StatsDimensionsValue statsDimensionsValue) {
        //TODO(b/73172999): Add robo test for this method
        if (statsDimensionsValue == null) {
            return UID_NULL;
        }
        if (statsDimensionsValue.isValueType(INT_VALUE_TYPE)
                && statsDimensionsValue.getField() == STATSD_UID_FILED) {
            // Find out the real uid
            return statsDimensionsValue.getIntValue();
        }
        if (statsDimensionsValue.isValueType(TUPLE_VALUE_TYPE)) {
            final List<StatsDimensionsValue> values = statsDimensionsValue.getTupleValueList();
            for (int i = 0, size = values.size(); i < size; i++) {
                int uid = extractUidFromStatsDimensionsValue(values.get(i));
                if (uid != UID_NULL) {
                    return uid;
                }
            }
        }

        return UID_NULL;
    }