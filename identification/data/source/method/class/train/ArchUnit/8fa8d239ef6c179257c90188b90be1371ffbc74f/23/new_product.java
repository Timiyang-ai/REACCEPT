@PublicAPI(usage = ACCESS)
        public static <HAS_NAME extends HasName> DescribedPredicate<HAS_NAME> nameMatching(final String regex) {
            return new NameMatchingPredicate<>(regex);
        }