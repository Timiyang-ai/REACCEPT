@Test
    public void testEncode(){
        String value = "={}[]今天天气很不错今天天气很不错今天天气很不错今天天气很不错今天天气很不错";
        value = "http://xy2.cbg.163.com/cgi-bin/equipquery.py?server_name=风花雪月&query_order=selling_time DESC&search_page&areaid=2&server_id=63&act=search_browse&equip_type_ids&search_text=斩妖剑";
        value = "斩妖剑";
        value = "风花雪月";
        LOGGER.info(URIUtil.encode(value, CharsetType.UTF8));
        value = "景儿,么么哒";
        LOGGER.info(URIUtil.encode(value, CharsetType.UTF8));
        LOGGER.info(URIUtil.encode("白色/黑色/纹理浅麻灰", CharsetType.UTF8));
        LOGGER.info(URIUtil.encode("Lifestyle / Graphic,", CharsetType.UTF8));
    }