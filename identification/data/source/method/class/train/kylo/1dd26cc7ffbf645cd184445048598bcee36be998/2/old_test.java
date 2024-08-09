    @Test
    public void resolveExpression() {
        final FeedMetadata metadata = new FeedMetadata();
        metadata.setSystemFeedName("myfeed");
        metadata.setDataTransformation(new FeedDataTransformation());
        metadata.getDataTransformation().setDatasourceIds(new ArrayList<>());

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

        //verify replacement with NiFi el
        final NifiProperty prop10 = createProperty("property1","a value");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop10));
        Assert.assertEquals("/path/to/property1,${nifi.expression.property}", prop10.getValue());

        //verify replacement without NiFi el
        final NifiProperty prop11 = createProperty("Another Processor","property1","a value");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop11));
        Assert.assertEquals("/path/to/another_processor/property1/location", prop11.getValue());

        //verify replacement without NiFi el using default processor type replacement
        final NifiProperty prop12 = createProperty("My New Processor","property1","a value");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop12));
        Assert.assertEquals("/path/to/property1/location", prop12.getValue());


        //verify replacement without NiFi el using default processor type replacement
        final NifiProperty extraFiles = createProperty("extra_files","a value");
        Assert.assertTrue(resolver.resolveExpression(metadata, extraFiles));
        Assert.assertEquals("${table_field_policy_json_file},/usr/hdp/current/spark-client/conf/hive-site.xml",extraFiles.getValue());
        Assert.assertTrue(resolver.resolveExpression(metadata, extraFiles));
        Assert.assertEquals("${table_field_policy_json_file},/usr/hdp/current/spark-client/conf/hive-site.xml",extraFiles.getValue());


        final NifiProperty hiveSchema = createProperty(STATIC_KEY,"${config.hive.schema}");
        Assert.assertTrue(resolver.resolveExpression(metadata, hiveSchema));
        Assert.assertEquals("hive",hiveSchema.getValue());

        // Verify datatransformation variables
        final NifiProperty prop13 = createProperty("${metadata.dataTransformation.datasourceIds}");
        Assert.assertFalse(resolver.resolveExpression(metadata, prop13));

        String uuid =UUID.randomUUID().toString();
        metadata.getDataTransformation().getDatasourceIds().add(uuid);
        final NifiProperty prop14 = createProperty("${metadata.dataTransformation.datasourceIds}");
        Assert.assertTrue(resolver.resolveExpression(metadata, prop14));
        Assert.assertEquals(uuid, prop14.getValue());


    }