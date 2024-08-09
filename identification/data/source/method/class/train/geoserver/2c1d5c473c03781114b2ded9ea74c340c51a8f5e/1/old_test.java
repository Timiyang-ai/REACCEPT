@Test
    public void testHasServiceSupport() throws Exception {
        Catalog cat = getCatalog();
        LayerGroupInfo lg = cat.getFactory().createLayerGroup();
        lg.setName("linkgroup");
        lg.setWorkspace(cat.getWorkspaceByName("sf"));
        lg.getLayers().add(cat.getLayerByName(getLayerId(MockData.PRIMITIVEGEOFEATURE)));
        new CatalogBuilder(cat).calculateLayerGroupBounds(lg);
        cat.add(lg);
        tester.startPage(MapPreviewPage.class);
        tester.assertRenderedPage(MapPreviewPage.class);
        MapPreviewPage page = (MapPreviewPage) tester.getLastRenderedPage();
        assertTrue(page.hasServiceSupport("sf:linkgroup", "WMS"));
    }