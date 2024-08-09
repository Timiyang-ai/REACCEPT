@Test
    public void removeParameter(){
        uriString = "http://www.feilong.com:8888/search.htm?keyword=中国&page=&categoryCode=2-5-3-11&label=TopSeller";
        String pageParamName = "label";
        LOGGER.info(ParamUtil.removeParameter(uriString, pageParamName, CharsetType.UTF8));
    }