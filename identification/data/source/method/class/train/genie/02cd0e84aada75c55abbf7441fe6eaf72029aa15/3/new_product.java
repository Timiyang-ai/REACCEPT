public T updateEntity(T entity) {
        LOG.debug("called");
        EntityManager em = createEntityManager();
        try {
            em.getTransaction().begin();
            T result = em.merge(entity);
            em.getTransaction().commit();
            return result;
        } finally {
            em.close();
        }
    }