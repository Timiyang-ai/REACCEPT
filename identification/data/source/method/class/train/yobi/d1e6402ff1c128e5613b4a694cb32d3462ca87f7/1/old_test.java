    @Test
    public void getFirstValueFromQuery() {
        {
            HashMap<String, String[]> query = new HashMap<>();
            String[] values = {"a", "b", "c"};
            query.put("test", values);
            String actual = HttpUtil.getFirstValueFromQuery(query, "test");
            assertThat(actual).isEqualTo("a");
        }

        {
            HashMap<String, String[]> query = new HashMap<>();
            String[] values = {};
            query.put("test", values);
            String actual = HttpUtil.getFirstValueFromQuery(query, "test");
            assertThat(actual).isEqualTo("");
        }

        {
            HashMap<String, String[]> query = new HashMap<>();
            String actual = HttpUtil.getFirstValueFromQuery(query, "test");
            assertThat(actual).isEqualTo("");
        }
    }