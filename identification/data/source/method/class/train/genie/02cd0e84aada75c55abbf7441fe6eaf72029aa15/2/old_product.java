public T deleteEntity(String id, Class<T> type) {
        logger.debug("called");
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();
            T entity = getEntity(id, type, em);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();

            return entity;
        } finally {
            em.close();
        }
    }