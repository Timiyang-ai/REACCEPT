    @Test
    public final void getFreshTest() {
        TypeScope scope = new TypeScope();
        final Type t = Type.tupleOf(
                Type.listOf(scope.getVar("a")),
                Type.fun(
                        scope.getVar("b"),
                        Type.con("String")
                )
        );

        assertFalse(t == t.getFresh());
        assertEquals(t.prettyPrint(), t.getFresh().prettyPrint());
    }