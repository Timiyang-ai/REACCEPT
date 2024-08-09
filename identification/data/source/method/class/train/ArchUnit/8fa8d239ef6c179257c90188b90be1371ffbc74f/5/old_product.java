@PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasName> nameMatching(final String regex) {
            final Pattern pattern = Pattern.compile(regex);
            return new DescribedPredicate<HasName>(String.format("name matching '%s'", regex)) {
                @Override
                public boolean apply(HasName input) {
                    return pattern.matcher(input.getName()).matches();
                }
            };
        }