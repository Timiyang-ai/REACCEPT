public static Timer createTimer(ZonedDateTime dateTime, Procedure0 closure) {
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("procedure", closure);
        return makeTimer(dateTime, closure.toString(), dataMap);
    }