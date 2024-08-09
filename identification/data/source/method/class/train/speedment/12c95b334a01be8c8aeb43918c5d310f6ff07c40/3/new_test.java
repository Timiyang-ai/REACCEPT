    @Test
    void transform() {
        final File file = File.of("com/example/Main.java")
            .add(Import.of(StringBuilder.class))
            .add(Class.of("Main").public_().final_()
                .add(Field.of("string", String.class).private_().final_())
                .add(Constructor.of()
                    .public_()
                    .add(SUPPRESS_WARNINGS_UNCHECKED)
                    .add(IllegalArgumentException.class)
                    .set(Javadoc.of("The constructor for this class."))
                    .add(Field.of("stringOrBuilder", CharSequence.class))
                    .add("if (stringOrBuilder instanceof String) " + block(
                        "this.string = (String) stringOrBuilder;"
                    ) + " else if () " + block(
                        "this.string = ((StringBuilder) stringOrBuilder).toString();"
                    ) + " else " + block(
                        "throw new IllegalArgumentException(\"Not a String or StringBuilder!\");"
                    ))
                )
            );

        final Generator generator = new JavaGenerator();
        final String code = generator.on(file).get();

        assertNotNull(code);

        Stream.of(
            "package com.example;",
            "public final class Main",
            "public Main(",
            String.class.getSimpleName(),
            StringBuilder.class.getSimpleName(),
            "Main",
            "public",
            "final",
            "private"
        ).forEach(s -> {
            assertTrue(code.contains(s));
        });

    }