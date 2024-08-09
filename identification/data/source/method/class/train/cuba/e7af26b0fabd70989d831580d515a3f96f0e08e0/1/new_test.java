    @Test
    public void removeField() {
        FieldGroup fieldGroup = uiComponents.create(FieldGroup.class);

        Datasource<User> testDs = createTestDs();

        FieldGroup.FieldConfig fcName = fieldGroup.createField("name");
        fcName.setProperty("name");
        fcName.setEditable(false);
        fcName.setDatasource(testDs);
        fieldGroup.addField(fcName);

        FieldGroup.FieldConfig fcLogin = fieldGroup.createField("login");
        fcLogin.setProperty("login");
        fcLogin.setEnabled(false);
        fcLogin.setVisible(false);
        fcLogin.setDatasource(testDs);
        fieldGroup.addField(fcLogin);

        fieldGroup.removeField("login");

        assertEquals(fcName, fieldGroup.getField("name"));
        assertEquals(null, fieldGroup.getField("login"));

        fieldGroup.bind();

        assertEquals(1, getGridRows(fieldGroup));
        assertEquals(1, getGridColumns(fieldGroup));

        assertEquals(getComposition(fcName), getGridCellComposition(fieldGroup, 0, 0));
    }