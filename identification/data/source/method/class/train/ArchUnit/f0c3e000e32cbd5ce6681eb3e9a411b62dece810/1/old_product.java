@PublicAPI(usage = ACCESS)
        public Slices matching(String packageIdentifier) {
            SliceBuilders sliceBuilders = new SliceBuilders();
            PackageMatcher matcher = PackageMatcher.of(packageIdentifier);
            for (JavaClass clazz : classes) {
                Optional<List<String>> groups = matcher.match(clazz.getPackage()).transform(TO_GROUPS);
                sliceBuilders.add(groups, clazz);
            }
            return new Slices(sliceBuilders.build()).as(String.format("slices matching '%s'", packageIdentifier));
        }