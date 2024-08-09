@Test
    public void testGetReferenceBeans() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestBean.class);

        ReferenceAnnotationBeanPostProcessor beanPostProcessor = context.getBean(BEAN_NAME,
                ReferenceAnnotationBeanPostProcessor.class);

        Collection<ReferenceBean<?>> referenceBeans = beanPostProcessor.getReferenceBeans();

        /**
         * 1 -> demoService、demoServiceShouldBeSame
         * 1 -> demoServiceShouldNotBeSame
         * 1 -> demoServiceWithArray、demoServiceWithArrayShouldBeSame
         */
        Assert.assertEquals(3, referenceBeans.size());

        ReferenceBean<?> referenceBean = referenceBeans.iterator().next();

        TestBean testBean = context.getBean(TestBean.class);

        Assert.assertEquals(referenceBean.get(), testBean.getDemoServiceFromAncestor());
        Assert.assertEquals(referenceBean.get(), testBean.getDemoServiceFromParent());
        Assert.assertEquals(referenceBean.get(), testBean.getDemoService());

    }