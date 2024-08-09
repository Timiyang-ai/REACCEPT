@Test
    public void setRequired_nullValueBecomesDefaultValue() {
        if (type == ObjectSchemaType.IMMUTABLE) {
            return;
        }
        for (FieldType fieldType : FieldType.values()) {
            String fieldName = fieldType.name();
            switch (fieldType) {
                case OBJECT:
                case LIST:
                    // Skip always nullable fields.
                    break;
                default:
                    // Skip not-nullable fields .
                    if (!fieldType.isNullable()) {
                        break;
                    }
                    schema.addField(fieldName, fieldType.getType());
                    DynamicRealmObject object = ((DynamicRealm)realm).createObject(schema.getClassName());
                    assertTrue(object.isNull(fieldName));
                    schema.setRequired(fieldName, true);
                    assertFalse(object.isNull(fieldName));
                    if (fieldType == FieldType.BLOB) {
                        assertEquals(0, object.getBlob(fieldName).length);
                    } else if (fieldType == FieldType.BOOLEAN) {
                        assertFalse(object.getBoolean(fieldName));
                    } else if (fieldType == FieldType.STRING) {
                        assertEquals(0, object.getString(fieldName).length());
                    } else if (fieldType == FieldType.FLOAT) {
                        assertEquals(0.0F, object.getFloat(fieldName), 0F);
                    } else if (fieldType == FieldType.DOUBLE) {
                        assertEquals(0.0D, object.getDouble(fieldName), 0D);
                    } else if (fieldType == FieldType.DATE) {
                        assertEquals(new Date(0), object.getDate(fieldName));
                    } else {
                        assertEquals(0, object.getInt(fieldName));
                    }
                    break;
            }
        }
    }