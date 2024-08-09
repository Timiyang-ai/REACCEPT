    public void test_build() {
        float titleBoost = 0.01f;
        float contentBoost = 0.005f;

        assertQuery(functionScoreQuery(simpleQuery("QUERY", titleBoost, contentBoost)), buildQuery("QUERY"));
        assertQuery(functionScoreQuery(simpleQuery("QUERY", titleBoost, contentBoost)), buildQuery(" QUERY"));
        assertQuery(functionScoreQuery(simpleQuery("QUERY", titleBoost, contentBoost)), buildQuery("QUERY "));

        assertQuery(
                functionScoreQuery(andQuery(simpleQuery("QUERY1", titleBoost, contentBoost),
                        simpleQuery("QUERY2", titleBoost, contentBoost))), buildQuery("QUERY1 QUERY2"));
        assertQuery(
                functionScoreQuery(andQuery(simpleQuery("QUERY1", titleBoost, contentBoost),
                        simpleQuery("QUERY2", titleBoost, contentBoost))), buildQuery("QUERY1 AND QUERY2"));

        assertQuery(
                functionScoreQuery(orQuery(simpleQuery("QUERY1", titleBoost, contentBoost), simpleQuery("QUERY2", titleBoost, contentBoost))),
                buildQuery("QUERY1 OR QUERY2"));

        assertQueryBuilder("test", "", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("test", "test", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("test", "a", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("test", "あ", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("test", "ア", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("test", "亜", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("test", "아", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("title", "test", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("title", "a", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("title", "あ", PrefixQueryBuilder.class);
        assertQueryBuilder("title", "ああ", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("title", "ア", PrefixQueryBuilder.class);
        assertQueryBuilder("title", "アア", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("title", "亜", PrefixQueryBuilder.class);
        assertQueryBuilder("title", "亜亜", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("title", "아", PrefixQueryBuilder.class);
        assertQueryBuilder("title", "아아", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("content", "test", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("content", "a", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("content", "あ", PrefixQueryBuilder.class);
        assertQueryBuilder("content", "ああ", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("content", "ア", PrefixQueryBuilder.class);
        assertQueryBuilder("content", "アア", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("content", "亜", PrefixQueryBuilder.class);
        assertQueryBuilder("content", "亜亜", MatchPhraseQueryBuilder.class);
        assertQueryBuilder("content", "아", PrefixQueryBuilder.class);
        assertQueryBuilder("content", "아아", MatchPhraseQueryBuilder.class);

        assertEquals(
                "{\"function_score\":{\"query\":{\"prefix\":{\"site\":{\"value\":\"fess.codelibs.org\",\"boost\":1.0}}},\"functions\":[{\"filter\":{\"match_all\":{\"boost\":1.0}},\"field_value_factor\":{\"field\":\"boost\",\"factor\":1.0,\"modifier\":\"none\"}}],\"score_mode\":\"multiply\",\"max_boost\":3.4028235E38,\"boost\":1.0}}",
                buildQuery("site:fess.codelibs.org").toString().replaceAll("\\s", ""));

        assertEquals(
                "{\"function_score\":{\"query\":{\"wildcard\":{\"title\":{\"wildcard\":\"*\",\"boost\":1.0}}},\"functions\":[{\"filter\":{\"match_all\":{\"boost\":1.0}},\"field_value_factor\":{\"field\":\"boost\",\"factor\":1.0,\"modifier\":\"none\"}}],\"score_mode\":\"multiply\",\"max_boost\":3.4028235E38,\"boost\":1.0}}",
                buildQuery("allintitle:").toString().replaceAll("\\s", ""));
        assertEquals(
                "{\"function_score\":{\"query\":{\"match_phrase\":{\"title\":{\"query\":\"test\",\"slop\":0,\"zero_terms_query\":\"NONE\",\"boost\":1.0}}},\"functions\":[{\"filter\":{\"match_all\":{\"boost\":1.0}},\"field_value_factor\":{\"field\":\"boost\",\"factor\":1.0,\"modifier\":\"none\"}}],\"score_mode\":\"multiply\",\"max_boost\":3.4028235E38,\"boost\":1.0}}",
                buildQuery("allintitle:test").toString().replaceAll("\\s", ""));
        assertEquals(
                "{\"function_score\":{\"query\":{\"match_phrase\":{\"title\":{\"query\":\"test\",\"slop\":0,\"zero_terms_query\":\"NONE\",\"boost\":1.0}}},\"functions\":[{\"filter\":{\"match_all\":{\"boost\":1.0}},\"field_value_factor\":{\"field\":\"boost\",\"factor\":1.0,\"modifier\":\"none\"}}],\"score_mode\":\"multiply\",\"max_boost\":3.4028235E38,\"boost\":1.0}}",
                buildQuery("allintitle: test").toString().replaceAll("\\s", ""));
        assertEquals(
                "{\"function_score\":{\"query\":{\"bool\":{\"must\":[{\"match_phrase\":{\"title\":{\"query\":\"aaa\",\"slop\":0,\"zero_terms_query\":\"NONE\",\"boost\":1.0}}},{\"match_phrase\":{\"title\":{\"query\":\"bbb\",\"slop\":0,\"zero_terms_query\":\"NONE\",\"boost\":1.0}}}],\"adjust_pure_negative\":true,\"boost\":1.0}},\"functions\":[{\"filter\":{\"match_all\":{\"boost\":1.0}},\"field_value_factor\":{\"field\":\"boost\",\"factor\":1.0,\"modifier\":\"none\"}}],\"score_mode\":\"multiply\",\"max_boost\":3.4028235E38,\"boost\":1.0}}",
                buildQuery("allintitle: aaa bbb").toString().replaceAll("\\s", ""));

        assertEquals(
                "{\"function_score\":{\"query\":{\"wildcard\":{\"url\":{\"wildcard\":\"*\",\"boost\":1.0}}},\"functions\":[{\"filter\":{\"match_all\":{\"boost\":1.0}},\"field_value_factor\":{\"field\":\"boost\",\"factor\":1.0,\"modifier\":\"none\"}}],\"score_mode\":\"multiply\",\"max_boost\":3.4028235E38,\"boost\":1.0}}",
                buildQuery("allinurl:").toString().replaceAll("\\s", ""));
        assertEquals(
                "{\"function_score\":{\"query\":{\"wildcard\":{\"url\":{\"wildcard\":\"*test*\",\"boost\":1.0}}},\"functions\":[{\"filter\":{\"match_all\":{\"boost\":1.0}},\"field_value_factor\":{\"field\":\"boost\",\"factor\":1.0,\"modifier\":\"none\"}}],\"score_mode\":\"multiply\",\"max_boost\":3.4028235E38,\"boost\":1.0}}",
                buildQuery("allinurl:test").toString().replaceAll("\\s", ""));
        assertEquals(
                "{\"function_score\":{\"query\":{\"wildcard\":{\"url\":{\"wildcard\":\"*test*\",\"boost\":1.0}}},\"functions\":[{\"filter\":{\"match_all\":{\"boost\":1.0}},\"field_value_factor\":{\"field\":\"boost\",\"factor\":1.0,\"modifier\":\"none\"}}],\"score_mode\":\"multiply\",\"max_boost\":3.4028235E38,\"boost\":1.0}}",
                buildQuery("allinurl: test").toString().replaceAll("\\s", ""));
        assertEquals(
                "{\"function_score\":{\"query\":{\"bool\":{\"must\":[{\"wildcard\":{\"url\":{\"wildcard\":\"*aaa*\",\"boost\":1.0}}},{\"wildcard\":{\"url\":{\"wildcard\":\"*bbb*\",\"boost\":1.0}}}],\"adjust_pure_negative\":true,\"boost\":1.0}},\"functions\":[{\"filter\":{\"match_all\":{\"boost\":1.0}},\"field_value_factor\":{\"field\":\"boost\",\"factor\":1.0,\"modifier\":\"none\"}}],\"score_mode\":\"multiply\",\"max_boost\":3.4028235E38,\"boost\":1.0}}",
                buildQuery("allinurl: aaa bbb").toString().replaceAll("\\s", ""));
    }