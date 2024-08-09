@Test
public void getExistingObject_shouldReturnDrug() {
    DrugEditor drugEditor = new DrugEditor();
    Drug existingDrug = drugEditor.getExistingObject();
    Assert.assertNotNull(existingDrug);
    Assert.assertEquals(EXISTING_ID, existingDrug.getId());
}