    @Test
    public void getParamValueStringTest() {
        try {
            DummyCommand dc = new DummyCommand();
            Class<?> cl = dc.getClass();
            AnnotatedElement ae = (AnnotatedElement)cl.getDeclaredField("foo");
            Param param = ae.getAnnotation(Param.class);
            ParameterMap params = new ParameterMap();
            params.set("foo", "true");
            String val =
                MapInjectionResolver.getParamValueString(params, param, ae, null);
            assertEquals("val should be true", "true", val);

            ae = (AnnotatedElement)cl.getDeclaredField("bar");
            param = ae.getAnnotation(Param.class);
            val = MapInjectionResolver.getParamValueString(params, param, ae, null);
            assertEquals("val should be false", "false", val);

            ae = (AnnotatedElement)cl.getDeclaredField("hello");
            param = ae.getAnnotation(Param.class);
            val = MapInjectionResolver.getParamValueString(params, param, ae, null);
            assertEquals("val should be null", null, val);
            
            ae = (AnnotatedElement)cl.getDeclaredField("dyn");
            param = ae.getAnnotation(Param.class);
            val = MapInjectionResolver.getParamValueString(params, param, ae, null);
            assertEquals("val should be dynamic-default-value", "dynamic-default-value", val);
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }