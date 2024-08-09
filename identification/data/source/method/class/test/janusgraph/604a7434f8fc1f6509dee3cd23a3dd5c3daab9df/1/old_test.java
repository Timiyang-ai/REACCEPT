    @Test
    public void createElements() throws ConfigurationException {
        app.createElements();

        assertEquals(12L, g.V().count().next().longValue());
        assertEquals(1L, g.V().hasLabel("titan").count().next().longValue());
        assertEquals(1L, g.V().hasLabel("demigod").count().next().longValue());
        assertEquals(1L, g.V().hasLabel("human").count().next().longValue());
        assertEquals(3L, g.V().hasLabel("location").count().next().longValue());
        assertEquals(3L, g.V().hasLabel("god").count().next().longValue());
        assertEquals(3L, g.V().hasLabel("monster").count().next().longValue());

        assertEquals(17L, g.E().count().next().longValue());
        assertEquals(2L, g.E().hasLabel("father").count().next().longValue());
        assertEquals(1L, g.E().hasLabel("mother").count().next().longValue());
        assertEquals(6L, g.E().hasLabel("brother").count().next().longValue());
        assertEquals(1L, g.E().hasLabel("pet").count().next().longValue());
        assertEquals(4L, g.E().hasLabel("lives").count().next().longValue());
        assertEquals(3L, g.E().hasLabel("battled").count().next().longValue());
        final float[] place = (float[]) g.V().has("name", "hercules").outE("battled").has("time", 12).values("place")
                .next();
        assertNotNull(place);
        assertEquals(2, place.length);
        assertEquals(Float.valueOf(39f), Float.valueOf(place[0]));
        assertEquals(Float.valueOf(22f), Float.valueOf(place[1]));
    }