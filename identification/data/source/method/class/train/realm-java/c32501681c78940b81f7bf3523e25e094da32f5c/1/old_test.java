@Test
    public void distinct() {
        final long numberOfBlocks = 25;
        final long numberOfObjects = 10; // must be greater than 1

        populateForDistinct(realm, numberOfBlocks, numberOfObjects, false);

        RealmResults<DynamicRealmObject> distinctBool = realm.distinct(AnnotationIndexTypes.CLASS_NAME, "indexBoolean");
        assertEquals(2, distinctBool.size());

        for (String fieldName : new String[]{"Long", "Date", "String"}) {
            RealmResults<DynamicRealmObject> distinct = realm.distinct(AnnotationIndexTypes.CLASS_NAME, "index" + fieldName);
            assertEquals("index" + fieldName, numberOfBlocks, distinct.size());
        }
    }