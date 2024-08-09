public Pair<Double, Long> getRPSForSecond(final double elapsedSinceStartOfTestSec) {
        JMeterProperty data = getData();
        if (data instanceof NullProperty) {
            return Pair.of(-1.0, 0L);
        }
        CollectionProperty rows = (CollectionProperty) data;
        PropertyIterator scheduleIT = rows.iterator();
        double newSec = elapsedSinceStartOfTestSec;
        double result = -1;
        boolean resultComputed = false;
        long totalDuration = 0;
        while (scheduleIT.hasNext()) {
            @SuppressWarnings("unchecked")
            List<Object> curProp = (List<Object>) scheduleIT.next().getObjectValue();
            int duration = getIntValue(curProp, DURATION_FIELD_NO);
            totalDuration += duration;
            if(!resultComputed) {
                double fromRps = getDoubleValue(curProp, FROM_FIELD_NO);
                double toRps = getDoubleValue(curProp, TO_FIELD_NO);
                if (newSec - duration <= 0) {
                    result = fromRps + newSec * (toRps - fromRps) / (double) duration;
                    resultComputed = true;
                } else {
                    // We're not yet in the slot
                    newSec -= duration;
                }
            }
        }
        return Pair.of(result, totalDuration);
    }