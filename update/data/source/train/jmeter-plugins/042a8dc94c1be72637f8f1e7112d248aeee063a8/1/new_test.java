@Test
    public void testCollectionPropertyToTableModelRows() {
        System.out.println("collectionPropertyToTableModelRows");
        String propname = "prop";
        PowerTableModel modelSrc = getTestModel();
        CollectionProperty propExp = JMeterPluginsUtils.tableModelRowsToCollectionProperty(modelSrc, propname);
        PowerTableModel modelDst = getTestModel();
        modelDst.clearData();
        JMeterPluginsUtils.collectionPropertyToTableModelRows(propExp, modelDst);
        CollectionProperty propRes = JMeterPluginsUtils.tableModelRowsToCollectionProperty(modelDst, propname);
        assertEquals(propExp.toString(), propRes.toString());
    }