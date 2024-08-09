    @Test
    @DataProvider(value = {
            "some.arbitrary.pkg | some.arbitrary.pkg             | true",
            "some.arbitrary.pkg | some.thing.different           | false",
            "some..pkg          | some.arbitrary.pkg             | true",
            "some..middle..pkg  | some.arbitrary.middle.more.pkg | true",
            "*..pkg             | some.arbitrary.pkg             | true",
            "some..*            | some.arbitrary.pkg             | true",
            "*..pkg             | some.arbitrary.pkg.toomuch     | false",
            "toomuch.some..*    | some.arbitrary.pkg             | false",
            "*..wrong           | some.arbitrary.pkg             | false",
            "some..*            | wrong.arbitrary.pkg            | false",
            "..some             | some                           | true",
            "some..             | some                           | true",
            "*..some            | some                           | false",
            "some..*            | some                           | false",
            "..some             | asome                          | false",
            "some..             | somea                          | false",
            "*.*.*              | wrong.arbitrary.pkg            | true",
            "*.*.*              | wrong.arbitrary.pkg.toomuch    | false",
            "some.arbi*.pk*..   | some.arbitrary.pkg.whatever    | true",
            "some.arbi*..       | some.brbitrary.pkg             | false",
            "some.*rary.*kg..   | some.arbitrary.pkg.whatever    | true",
            "some.*rary..       | some.arbitrarz.pkg             | false",
            "some.pkg           | someepkg                       | false",
            "..pkg..            | some.random.pkg.maybe.anywhere | true",
            "..p..              | s.r.p.m.a                      | true",
            "*..pkg..*          | some.random.pkg.maybe.anywhere | true",
            "*..p..*            | s.r.p.m.a                      | true"
    }, splitBy = "\\|")
    public void match(String matcher, String target, boolean matches) {
        assertThat(PackageMatcher.of(matcher).matches(target))
                .as("package matches")
                .isEqualTo(matches);
    }