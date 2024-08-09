@Test
    public void testRemove(){
        List<String> list = new ArrayList<String>(){

            /**
             * 
             */
            private static final long serialVersionUID = -9002323146501447769L;

            {
                add("xinge");
                add("feilong1");
                add("feilong2");
                add("feilong2");
            }
        };

        LOGGER.info("list:{}", JsonUtil.format(CollectionsUtil.remove(list, "feilong2")));
        LOGGER.info("list:{}", JsonUtil.format(list));
    }