    @Test
    public void generateTitleAndContent() throws Exception {
        Pair<String, String> pair = decoratorManager.generateTitleAndContent(alert, true);
        String title = pair.getKey();
        String content = pair.getValue();
        logger.info("title: {}", title);
        logger.info("content: {}",content);

    }