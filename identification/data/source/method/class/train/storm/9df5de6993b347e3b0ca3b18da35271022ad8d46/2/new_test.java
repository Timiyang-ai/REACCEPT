@Test
        public void testFindNMatches() {
            List<Path> files = new ArrayList<>();
            files.add(new File(String.join(File.separator, "src", "test", "resources"),
                    "logviewer-search-context-tests.log.test").toPath());
            files.add(new File(String.join(File.separator, "src", "test", "resources"),
                    "logviewer-search-context-tests.log.gz").toPath());

            final LogviewerLogSearchHandler handler = getSearchHandler();

            final List<Map<String, Object>> matches1 = handler.findNMatches(files, 20, 0, 0, "needle").getMatches();
            final List<Map<String, Object>> matches2 = handler.findNMatches(files, 20, 0, 126, "needle").getMatches();
            final List<Map<String, Object>> matches3 = handler.findNMatches(files, 20, 1, 0, "needle").getMatches();

            assertEquals(2, matches1.size());
            assertEquals(4, ((List) matches1.get(0).get("matches")).size());
            assertEquals(4, ((List) matches1.get(1).get("matches")).size());
            assertEquals(String.join(File.separator, "test", "resources", "logviewer-search-context-tests.log.test"), matches1.get(0).get("fileName"));
            assertEquals(String.join(File.separator, "test", "resources", "logviewer-search-context-tests.log.gz"), matches1.get(1).get("fileName"));

            assertEquals(2, ((List) matches2.get(0).get("matches")).size());
            assertEquals(4, ((List) matches2.get(1).get("matches")).size());

            assertEquals(1, matches3.size());
            assertEquals(4, ((List) matches3.get(0).get("matches")).size());
        }