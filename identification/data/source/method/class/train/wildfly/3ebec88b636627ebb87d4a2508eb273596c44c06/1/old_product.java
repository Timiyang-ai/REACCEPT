public String invalidateQuery(){
		
		EntityManager em = emf.createEntityManager();
		Statistics stats = em.unwrap(Session.class).getSessionFactory().getStatistics();
		stats.clear();

		try{
			String queryString = "from Employee e where e.id > 1";
			QueryStatistics queryStats = stats.getQueryStatistics(queryString);
			Query query = em.createQuery(queryString);
			query.setHint("org.hibernate.cacheable", true);

			// query - this call should fill the cache
			query.getResultList();
			assertEquals("Expected 1 put in cache"+generateQueryCacheStats(queryStats), 1,  queryStats.getCachePutCount());
			assertEquals("Expected no hits in cache"+generateQueryCacheStats(queryStats), 0,  queryStats.getCacheHitCount());
			
			// query - should hit cache
			query.getResultList();
			assertEquals("Expected 1 hit in cache"+generateQueryCacheStats(queryStats), 1,  queryStats.getCacheHitCount());
			
			// invalidate cache
			createEmployee(em, "Newman", "Paul", 4);
			
			// first call should miss and the second should hit the cache
			query.getResultList();
			assertEquals("Expected 2x miss in cache"+generateQueryCacheStats(queryStats), 2,  queryStats.getCacheMissCount());

			query.getResultList();
			assertEquals("Expected 2 hits in cache"+generateQueryCacheStats(queryStats), 2,  queryStats.getCacheHitCount());

		}catch (AssertionError e) {
			return e.getMessage();
		}	finally{
			em.close();
		}
		return "OK";
	}