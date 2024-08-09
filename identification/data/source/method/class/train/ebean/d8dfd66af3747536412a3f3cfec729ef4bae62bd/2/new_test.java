  @Test
  public void findIdsByParentId() {

    ResetBasicData.reset();

    List<Object> customerIds = new ArrayList<>();
    customerIds.add(1L);
    customerIds.add(2L);

    List<Object> contactIdsForOne = contacts().findIdsByParentId(1L, null, null, null, true);

    List<Object> contactIdsForMultiple = contacts().findIdsByParentId(null, customerIds, null, null, true);

    assertThat(contactIdsForOne).isNotEmpty();
    assertThat(contactIdsForMultiple).isNotEmpty();
  }