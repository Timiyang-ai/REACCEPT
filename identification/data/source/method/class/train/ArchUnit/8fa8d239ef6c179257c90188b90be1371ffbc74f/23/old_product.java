@PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasName> nameMatching(final String regex) {
            return new NameMatchingPredicate(regex);
        }