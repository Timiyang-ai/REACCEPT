public Pair<String, String> generateTitleAndContent(AlertEntity alert, boolean isAlertMessage) {
        Decorator decorator = getDecorator(isAlertMessage);
        String content = decorator.generateContent(alert);
        String title = decorator.generateTitle(alert);
        return new Pair<>(title, content);
    }