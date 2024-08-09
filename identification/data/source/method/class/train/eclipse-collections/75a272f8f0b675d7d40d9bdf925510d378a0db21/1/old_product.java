public static void assertContainsAllEntries(
            String multimapName,
            Multimap<?, ?> actualMultimap,
            Object... expectedKeyValues)
    {
        try
        {
            Verify.assertNotEmpty("Expected keys/values in assertion", expectedKeyValues);

            if (expectedKeyValues.length % 2 != 0)
            {
                Assert.fail("Odd number of keys and values (every key must have a value)");
            }

            Verify.assertObjectNotNull(multimapName, actualMultimap);

            MutableList<Map.Entry<?, ?>> missingEntries = Lists.mutable.of();
            for (int i = 0; i < expectedKeyValues.length; i += 2)
            {
                Object expectedKey = expectedKeyValues[i];
                Object expectedValue = expectedKeyValues[i + 1];

                if (!actualMultimap.containsKeyAndValue(expectedKey, expectedValue))
                {
                    missingEntries.add(new ImmutableEntry<Object, Object>(expectedKey, expectedValue));
                }
            }

            if (!missingEntries.isEmpty())
            {
                Assert.fail(multimapName + " is missing entries: " + missingEntries);
            }
        }
        catch (AssertionError e)
        {
            Verify.throwMangledException(e);
        }
    }