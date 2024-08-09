@Test
    public void remove_classWithReferencesThrows() {
        if (type == SchemaType.IMMUTABLE) {
            return;
        }

        try {
            realmSchema.remove("Cat");
            fail();
        } catch (IllegalStateException ignored) {
        }

        RealmObjectSchema ownerSchema = realmSchema.get("Owner");
        RealmObjectSchema catSchema = realmSchema.get("Cat");
        ownerSchema.removeField("cat");
        catSchema.removeField("owner");
        realmSchema.remove("Cat");
        assertFalse(realmSchema.contains("Cat"));
    }