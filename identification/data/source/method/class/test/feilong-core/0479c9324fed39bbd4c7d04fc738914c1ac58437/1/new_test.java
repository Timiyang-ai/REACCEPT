@Test
    public void removeParameterList(){
        uriString = "http://www.feilong.com:8888/search.htm?keyword=中国&page=&categoryCode=2-5-3-11&label=TopSeller";
        List<String> paramNameList = new ArrayList<String>();
        paramNameList.add("label");
        paramNameList.add("keyword");
        LOGGER.info(ParamUtil.removeParameterList(uriString, paramNameList, CharsetType.UTF8));
    }