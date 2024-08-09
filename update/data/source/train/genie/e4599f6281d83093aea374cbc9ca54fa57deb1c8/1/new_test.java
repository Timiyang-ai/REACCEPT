@Test
    public void testTagsToString() {

        Assert.assertEquals("", initialSetupTask.tagsToString(null));

        final String[][] inputs = new String[][] {
            {},
            {"some.tag:t"},
            {"foo", "bar"},
            {"bar", "foo"},
            {"foo", "bar", "tag,with,commas"},
            {"foo", "bar", "tag with spaces"},
            {"foo", "bar", "tag\nwith\nnewlines"},
            {"foo", "bar", "\"tag-with-double-quotes\""},
            {"foo", "bar", "\'tag-with-single-quotes\'"},
        };

        final String[] outputs = {
            "",
            "some.tag:t",
            "bar,foo",
            "bar,foo",
            "bar,foo,tag,with,commas", // Commas in tags are not escaped
            "bar,foo,tag with spaces",
            "bar,foo,tag\nwith\nnewlines",
            "\"tag-with-double-quotes\",bar,foo",
            "\'tag-with-single-quotes\',bar,foo",
        };

        Assert.assertEquals(inputs.length, outputs.length);
        for (int i = 0; i < inputs.length; i++) {
            final String expectedOutputString = outputs[i];
            final HashSet<String> tags = new HashSet<>(Arrays.asList(inputs[i]));
            Assert.assertEquals(expectedOutputString, this.initialSetupTask.tagsToString(tags));
        }
    }