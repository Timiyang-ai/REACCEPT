@PublicAPI(usage = ACCESS)
            public static <HAS_FULL_NAME extends HasName.AndFullName> DescribedPredicate<HAS_FULL_NAME> fullNameMatching(String regex) {
                return new FullNameMatchingPredicate<>(regex);
            }