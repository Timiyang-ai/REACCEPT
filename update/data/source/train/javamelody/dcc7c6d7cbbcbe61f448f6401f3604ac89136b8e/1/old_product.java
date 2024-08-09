String execute(Collector collector, CollectorServer collectorServer, String counterName, // NOPMD
			String sessionId, String threadId, String jobId) throws IOException {
		// CHECKSTYLE:ON
		String messageForReport;
		switch (this) {
		case CLEAR_COUNTER:
			assert collector != null;
			assert counterName != null;
			messageForReport = clearCounter(collector, counterName);
			break;
		case MAIL_TEST:
			assert collector != null;
			messageForReport = mailTest(collector, collectorServer);
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
				if (JAVA_VENDOR.contains("IBM")) {
					ibmHeapDump();
					messageForReport = I18N.getString("heap_dump_genere_ibm");
				} else {
					// heap dump à générer dans le répertoire temporaire sur le serveur
					// avec un suffixe contenant le host, la date et l'heure et avec une extension hprof
					// (utiliser jvisualvm du jdk ou MAT d'eclipse en standalone ou en plugin)
					final String heapDumpPath = heapDump().getPath();
					messageForReport = I18N.getFormattedString("heap_dump_genere",
							heapDumpPath.replace('\\', '/'));
				}
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
			assert jobId != null;
			messageForReport = pauseJob(jobId);
			break;
		case RESUME_JOB:
			assert jobId != null;
			messageForReport = resumeJob(jobId);
			break;
		case PURGE_OBSOLETE_FILES:
			assert collector != null;
			collector.deleteObsoleteFiles();
			messageForReport = I18N.getString("fichiers_obsoletes_purges") + '\n'
					+ I18N.getString("Usage_disque") + ": "
					+ (collector.getDiskUsage() / 1024 / 1024 + 1) + ' ' + I18N.getString("Mo");
			break;
		default:
			throw new IllegalStateException(toString());
		}
		if (messageForReport != null) {
			// log pour information en debug
			LOG.debug("Action '" + this + "' executed. Result: "
					+ messageForReport.replace('\n', ' '));
		}
		return messageForReport;
	}