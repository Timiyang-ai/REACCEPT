public static Timer createTimer(AbstractInstant instant, Procedure0 closure) {
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("procedure", closure);
        return makeTimer(instant, closure.toString(), dataMap);
    }