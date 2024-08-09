    @Test
    public void findAllForDeveloper() throws Exception {
        assertThat(pushApplicationDao.findAllForDeveloper("Admin", 0, 10).getResultList()).hasSize(2);
        assertThat(pushApplicationDao.findAllForDeveloper("Dave The Drummer", 0, 10).getResultList()).hasSize(1);
        assertThat(pushApplicationDao.findAllForDeveloper("Dave The Drummer", 0, 10).getResultList()).extracting("name").containsOnly("Push App 3");
        assertThat(pushApplicationDao.findAllForDeveloper("Admin The Drummer", 0, 10).getResultList()).isEmpty();

        assertThat(pushApplicationDao.getNumberOfPushApplicationsForDeveloper("Dave The Drummer")).isEqualTo(1);
        assertThat(pushApplicationDao.getNumberOfPushApplicationsForDeveloper("Admin")).isEqualTo(2);

        // check all:
        assertThat(pushApplicationDao.getNumberOfPushApplicationsForDeveloper()).isEqualTo(3);
        assertThat(pushApplicationDao.findAll(0, 10).getAggregate().getCount()).isEqualTo(3);

        // check exact:
        assertThat(pushApplicationDao.findAllByPushApplicationID("123").getName()).isEqualTo("Push App 2");
    }