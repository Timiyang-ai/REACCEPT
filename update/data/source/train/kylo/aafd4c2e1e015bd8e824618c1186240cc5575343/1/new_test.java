@Test
    public void resolveExpression() {
        final FeedMetadata metadata = new FeedMetadata();
        metadata.setSystemFeedName("myfeed");

        // Verify config variable
        final NifiProperty prop1 = createProperty("${config.test.value}");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop1));
        Assert.assertEquals("hello-world", prop1.getValue());

        // Verify metadata variable
        final NifiProperty prop2 = createProperty("${metadata.systemFeedName}");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop2));
        Assert.assertEquals("myfeed", prop2.getValue());

        // Verify static config
        final NifiProperty prop3 = createProperty(STATIC_KEY, "${metadata.systemFeedName}");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop3));
        Assert.assertEquals("myapp", prop3.getValue());

        final NifiProperty prop4 = createProperty(STATIC_KEY, "${config.test.value}");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop4));
        Assert.assertEquals("hello-world", prop4.getValue());

        final NifiProperty prop5 = createProperty(STATIC_KEY, "");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop5));
        Assert.assertEquals("myapp", prop5.getValue());

        // Verify multiple variables
        final NifiProperty prop6 = createProperty("${metadata.systemFeedName}.${config.test.value}");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop6));
        Assert.assertEquals("myfeed.hello-world", prop6.getValue());

        // Verify multiple variables
        final NifiProperty prop7 = createProperty("$${${metadata.systemFeedName}.${config.test.value}}");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop7));
        Assert.assertEquals("${myfeed.hello-world}", prop7.getValue());

        // Verify multiple variables
        final NifiProperty prop8 = createProperty("${config.${metadata.systemFeedName}.${config.test.value}}");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop8));
        Assert.assertEquals("runtime value", prop8.getValue());

        // Verify static text
        final NifiProperty prop9 = createProperty("config.test.value");
        Assert.assertFalse(resolver.resolveExpression(metadata, prop9));
        Assert.assertEquals("config.test.value", prop9.getValue());
    }