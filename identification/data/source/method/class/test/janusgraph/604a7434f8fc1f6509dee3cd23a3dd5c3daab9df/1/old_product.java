public void createElements() {
        try {
            // naive check if the graph was previously created
            if (g.V().has("name", "saturn").hasNext()) {
                if (supportsTransactions) {
                    g.tx().rollback();
                }
                return;
            }
            LOGGER.info("creating elements");

            // see GraphOfTheGodsFactory.java

            Vertex saturn = g.addV("titan").property("name", "saturn").property("age", 10000).next();
            Vertex sky = g.addV("location").property("name", "sky").next();
            Vertex sea = g.addV("location").property("name", "sea").next();
            Vertex jupiter = g.addV("god").property("name", "jupiter").property("age", 5000).next();
            Vertex neptune = g.addV("god").property("name", "neptune").property("age", 4500).next();
            Vertex hercules = g.addV("demigod").property("name", "hercules").property("age", 30).next();
            Vertex alcmene = g.addV("human").property("name", "alcmene").property("age", 45).next();
            Vertex pluto = g.addV("god").property("name", "pluto").property("age", 4000).next();
            Vertex nemean = g.addV("monster").property("name", "nemean").next();
            Vertex hydra = g.addV("monster").property("name", "hydra").next();
            Vertex cerberus = g.addV("monster").property("name", "cerberus").next();
            Vertex tartarus = g.addV("location").property("name", "tartarus").next();

            g.V(jupiter).as("a").V(saturn).addE("father").from("a").next();
            g.V(jupiter).as("a").V(sky).addE("lives").property("reason", "loves fresh breezes").from("a").next();
            g.V(jupiter).as("a").V(neptune).addE("brother").from("a").next();
            g.V(jupiter).as("a").V(pluto).addE("brother").from("a").next();

            g.V(neptune).as("a").V(sea).addE("lives").property("reason", "loves waves").from("a").next();
            g.V(neptune).as("a").V(jupiter).addE("brother").from("a").next();
            g.V(neptune).as("a").V(pluto).addE("brother").from("a").next();

            g.V(hercules).as("a").V(jupiter).addE("father").from("a").next();
            g.V(hercules).as("a").V(alcmene).addE("mother").from("a").next();

            if (supportsGeoshape) {
                g.V(hercules).as("a").V(nemean).addE("battled").property("time", 1)
                        .property("place", Geoshape.point(38.1f, 23.7f)).from("a").next();
                g.V(hercules).as("a").V(hydra).addE("battled").property("time", 2)
                        .property("place", Geoshape.point(37.7f, 23.9f)).from("a").next();
                g.V(hercules).as("a").V(cerberus).addE("battled").property("time", 12)
                        .property("place", Geoshape.point(39f, 22f)).from("a").next();
            } else {
                g.V(hercules).as("a").V(nemean).addE("battled").property("time", 1)
                        .property("place", getGeoFloatArray(38.1f, 23.7f)).from("a").next();
                g.V(hercules).as("a").V(hydra).addE("battled").property("time", 2)
                        .property("place", getGeoFloatArray(37.7f, 23.9f)).from("a").next();
                g.V(hercules).as("a").V(cerberus).addE("battled").property("time", 12)
                        .property("place", getGeoFloatArray(39f, 22f)).from("a").next();
            }

            g.V(pluto).as("a").V(jupiter).addE("brother").from("a").next();
            g.V(pluto).as("a").V(neptune).addE("brother").from("a").next();
            g.V(pluto).as("a").V(tartarus).addE("lives").property("reason", "no fear of death").from("a").next();
            g.V(pluto).as("a").V(cerberus).addE("pet").from("a").next();

            g.V(cerberus).as("a").V(tartarus).addE("lives").from("a").next();

            if (supportsTransactions) {
                g.tx().commit();
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            if (supportsTransactions) {
                g.tx().rollback();
            }
        }
    }