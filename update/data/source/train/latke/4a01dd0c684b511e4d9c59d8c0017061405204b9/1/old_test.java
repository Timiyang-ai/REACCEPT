@Test(groups = {"jdbc"}, dataProvider = "createJsonData")
    public void remove(final JSONObject jsonObject) throws Exception {
        if (!ifRun) {
            return;
        }

        final Transaction transaction = jdbcRepository.beginTransaction();
        jdbcRepository.add(jsonObject);
        jdbcRepository.remove(jsonObject.getString(JdbcRepositories.OID));
        transaction.commit();

        final JSONObject jsonObjectDB = jdbcRepository.get(jsonObject.getString(JdbcRepositories.OID));

        assertNull(jsonObjectDB);

    }