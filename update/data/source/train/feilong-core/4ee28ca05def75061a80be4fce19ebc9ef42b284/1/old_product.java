public static Map<Integer, String> group(String regexPattern,CharSequence input){
        Matcher matcher = getMatcher(regexPattern, input);
        if (!matcher.matches()){
            LOGGER.debug("[not matches] ,\n\tregexPattern:[{}] \n\tinput:[{}]", regexPattern, input);
            return Collections.emptyMap();
        }
        int groupCount = matcher.groupCount();
        Map<Integer, String> map = MapUtil.newLinkedHashMap(groupCount + 1);
        for (int i = 0; i <= groupCount; ++i){
            //匹配的索引
            String groupValue = matcher.group(i); //map.put(0, matcher.group());// 捕获组是从 1 开始从左到右的索引.组0表示整个模式,因此表达式 m.group(0) 等效于 m.group().
            LOGGER.debug("matcher group[{}],start-end:[{}-{}],groupValue:[{}]", i, matcher.start(i), matcher.end(i), groupValue);
            map.put(i, groupValue);//groupValue
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("regexPattern:[{}],input:[{}],groupMap:{}", regexPattern, input, JsonUtil.format(map));
        }
        return map;
    }