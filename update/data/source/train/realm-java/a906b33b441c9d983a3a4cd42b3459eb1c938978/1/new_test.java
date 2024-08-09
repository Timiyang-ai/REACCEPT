@Test
    public void isEmpty_acrossLinkingObjectObjectLink() {
        createIsEmptyDataSet(realm);
        for (RealmFieldType type : SUPPORTED_IS_EMPTY_TYPES) {
            switch (type) {
                case STRING:
                    assertEquals(1, realm.where(AllJavaTypes.class).isEmpty(AllJavaTypes.FIELD_LO_OBJECT + "." + AllJavaTypes.FIELD_STRING).count());
                    break;
                case BINARY:
                    assertEquals(1, realm.where(AllJavaTypes.class).isEmpty(AllJavaTypes.FIELD_LO_OBJECT + "." + AllJavaTypes.FIELD_BINARY).count());
                    break;
                case LIST:
                    assertEquals(1, realm.where(AllJavaTypes.class).isEmpty(AllJavaTypes.FIELD_LO_OBJECT + "." + AllJavaTypes.FIELD_LIST).count());
                    break;
                case LINKING_OBJECTS:
                    assertEquals(1, realm.where(AllJavaTypes.class).isEmpty(AllJavaTypes.FIELD_LO_OBJECT + "." + AllJavaTypes.FIELD_LO_OBJECT).count());
                    assertEquals(1, realm.where(AllJavaTypes.class).isEmpty(AllJavaTypes.FIELD_LO_OBJECT + "." + AllJavaTypes.FIELD_LO_LIST).count());
                    break;
                default:
                    fail("Unknown type: " + type);
            }
        }
    }