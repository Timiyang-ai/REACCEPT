String execute(Collector collector, String counterName, String sessionId, String threadId)
			throws IOException {
		// CHECKSTYLE:ON
		String messageForReport;
		switch (this) {
		case CLEAR_COUNTER:
			assert collector != null;
			assert counterName != null;
			messageForReport = clearCounter(collector, counterName);
			break;
		case GC:
			if (GC_ENABLED) {
				// garbage collector
				final long before = Runtime.getRuntime().totalMemory()
						- Runtime.getRuntime().freeMemory();
				gc();
				final long after = Runtime.getRuntime().totalMemory()
						- Runtime.getRuntime().freeMemory();
				messageForReport = I18N.getFormattedString("ramasse_miette_execute",
						(before - after) / 1024);
			} else {
				messageForReport = I18N.getString("ramasse_miette_desactive");
			}
			break;
		case HEAP_DUMP:
			if (HEAP_DUMP_ENABLED) {
				// heap dump à générer dans le répertoire temporaire sur le serveur
				// avec un suffixe contenant le host, la date et l'heure et avec une extension hprof
				// (utiliser jvisualvm du jdk ou MAT d'eclipse en standalone ou en plugin)
				final String heapDumpPath = heapDump().getPath();
				messageForReport = I18N.getFormattedString("heap_dump_genere", heapDumpPath
						.replace('\\', '/'));
			} else {
				messageForReport = I18N.getString("heap_dump_not_good");
			}
			break;
		case INVALIDATE_SESSIONS:
			// invalidation des sessions http
			SessionListener.invalidateAllSessions();
			messageForReport = I18N.getString("sessions_http_invalidees");
			break;
		case INVALIDATE_SESSION:
			// invalidation d'une session http
			assert sessionId != null;
			SessionListener.invalidateSession(sessionId);
			messageForReport = I18N.getString("session_http_invalidee");
			break;
		case CLEAR_CACHES:
			clearCaches();
			messageForReport = I18N.getString("caches_purges");
			break;
		case KILL_THREAD:
			assert threadId != null;
			messageForReport = killThread(threadId);
			break;
		case PAUSE_JOB:
			pauseAllJobs();
			messageForReport = I18N.getString("all_jobs_paused");
			break;
		case RESUME_JOB:
			resumeAllJobs();
			messageForReport = I18N.getString("all_jobs_resumed");
			break;
		default:
			throw new IllegalStateException(toString());
		}
		return messageForReport;
	}