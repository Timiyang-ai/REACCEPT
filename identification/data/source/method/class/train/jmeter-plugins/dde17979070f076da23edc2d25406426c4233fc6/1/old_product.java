public double getRPSForSecond(final double elapsedSinceStartOfTestSec) {
        JMeterProperty data = getData();
        if (data instanceof NullProperty) {
            return -1;
        }
        CollectionProperty rows = (CollectionProperty) data;
        PropertyIterator scheduleIT = rows.iterator();
        double newSec = elapsedSinceStartOfTestSec;
        while (scheduleIT.hasNext()) {
            @SuppressWarnings("unchecked")
            List<Object> curProp = (List<Object>) scheduleIT.next().getObjectValue();
            int duration = getIntValue(curProp, DURATION_FIELD_NO);
            double fromRps = getDoubleValue(curProp, FROM_FIELD_NO);
            double toRps = getDoubleValue(curProp, TO_FIELD_NO);
            if (newSec - duration <= 0) {
                return fromRps + newSec * (toRps - fromRps) / (double) duration;
            } else {
                // We're not yet in the slot
                newSec -= duration;
            }
        }
        return -1;
    }