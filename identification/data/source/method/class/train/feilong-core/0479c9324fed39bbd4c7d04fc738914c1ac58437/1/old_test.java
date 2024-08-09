@Test
    public void removeParameter(){
        uri = "http://www.feilong.com:8888/search.htm?keyword=中国&page=&categoryCode=2-5-3-11&label=TopSeller";
        String pageParamName = "label";
        LOGGER.info(ParamUtil.removeParameter(uri, pageParamName, CharsetType.ISO_8859_1));
    }