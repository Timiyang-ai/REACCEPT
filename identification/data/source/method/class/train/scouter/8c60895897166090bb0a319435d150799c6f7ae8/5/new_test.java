    @Test
    public void applyTelegrafInputConfig() {
        System.setProperty("scouter.config", "./conf/testcase-scouter.conf");
        System.setProperty("input_telegraf_$xpu_space$_enabled", "true");
        System.setProperty("input_telegraf_$xpu_space$_debug_enabled", "true");
        System.setProperty("input_telegraf_$xpu_space$_counter_mappings", "xpu1:Sxpu1,xpu2:Sxpu2:S of xpu 2:%:false");
        System.setProperty("input_telegraf_$xpu_space$_objFamily_base", "TGFAMILY01");
        System.setProperty("input_telegraf_$xpu_space$_objType_base", "TGTYPE01");
        System.setProperty("input_telegraf_$xpu_space$_objType_append_tags", "t1,t2");
        System.setProperty("input_telegraf_$xpu_space$_objName_base", "TGNAME01");
        System.setProperty("input_telegraf_$xpu_space$_objName_append_tags", "t3,t4");
        System.setProperty("input_telegraf_$xpu_space$_host_tag", "tg_host");
        System.setProperty("input_telegraf_$xpu_space$_host_mappings", "h1:Sh1,h2:Sh2");

        Map<String, ScouterTgMtConfig> configMap =  Configure.getInstance().telegrafInputConfigMap;
        ScouterTgMtConfig tConfig = configMap.get("xpu_space");

        Assert.assertEquals(tConfig.isEnabled(), true);
        Assert.assertEquals(tConfig.isDebugEnabled(), true);
        Assert.assertEquals(tConfig.getObjFamilyBase(), ScouterTgMtConfig.getPrefix() + System.getProperty("input_telegraf_$xpu_space$_objFamily_base"));
        Assert.assertEquals(tConfig.getObjTypeBase(), System.getProperty("input_telegraf_$xpu_space$_objType_base"));
        Assert.assertEquals(tConfig.getObjNameBase(), ScouterTgMtConfig.getPrefix() + System.getProperty("input_telegraf_$xpu_space$_objName_base"));
        Assert.assertEquals(tConfig.getHostTag(), System.getProperty("input_telegraf_$xpu_space$_host_tag"));

        Counter xpu1Counter = tConfig.getCounterMapping().get("xpu1");
        Assert.assertEquals(xpu1Counter.getName(), "Sxpu1");

        Counter xpu2Counter = tConfig.getCounterMapping().get("xpu2");
        Assert.assertEquals(xpu2Counter.getName(), "Sxpu2");
        Assert.assertEquals(xpu2Counter.getDisplayName(), "S of xpu 2");
        Assert.assertEquals(xpu2Counter.getUnit(), "%");
        Assert.assertEquals(xpu2Counter.isTotal(), false);

        Assert.assertTrue(tConfig.getObjTypeAppendTags().contains("t1"));
        Assert.assertTrue(tConfig.getObjTypeAppendTags().contains("t2"));
        Assert.assertTrue(tConfig.getObjNameAppendTags().contains("t3"));
        Assert.assertTrue(tConfig.getObjNameAppendTags().contains("t4"));

        Assert.assertEquals(tConfig.getHostMapping().get("h1"), "Sh1");
        Assert.assertEquals(tConfig.getHostMapping().get("h2"), "Sh2");
    }