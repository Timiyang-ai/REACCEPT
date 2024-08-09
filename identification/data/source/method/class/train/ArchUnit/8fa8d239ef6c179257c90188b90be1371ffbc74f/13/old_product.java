public Optional<Result> match(String aPackage) {
        Matcher matcher = packagePattern.matcher(aPackage);
        return matcher.matches() ? Optional.of(new Result(matcher)) : Optional.<Result>absent();
    }