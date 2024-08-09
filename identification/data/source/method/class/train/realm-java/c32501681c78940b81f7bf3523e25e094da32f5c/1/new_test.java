@Test
    public void distinct() {
        final long numberOfBlocks = 25;
        final long numberOfObjects = 10; // must be greater than 1
        populateForDistinct(realm, numberOfBlocks, numberOfObjects, false);

        RealmResults<DynamicRealmObject> distinctBool = realm.distinct(AnnotationIndexTypes.CLASS_NAME, AnnotationIndexTypes.FIELD_INDEX_BOOL);
        assertEquals(2, distinctBool.size());
        for (String field : new String[]{AnnotationIndexTypes.FIELD_INDEX_LONG, AnnotationIndexTypes.FIELD_INDEX_DATE, AnnotationIndexTypes.FIELD_INDEX_STRING}) {
            RealmResults<DynamicRealmObject> distinct = realm.distinct(AnnotationIndexTypes.CLASS_NAME, field);
            assertEquals(field, numberOfBlocks, distinct.size());
        }
    }