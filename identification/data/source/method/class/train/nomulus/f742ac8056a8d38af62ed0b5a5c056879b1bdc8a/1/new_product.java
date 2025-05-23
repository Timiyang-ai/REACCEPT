public void handleRequest(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
    checkNotNull(req);
    checkNotNull(rsp);
    Action.Method method;
    try {
      method = Action.Method.valueOf(req.getMethod());
    } catch (IllegalArgumentException e) {
      logger.infofmt("Unsupported method: %s", req.getMethod());
      rsp.sendError(SC_METHOD_NOT_ALLOWED);
      return;
    }
    String path = req.getRequestURI();
    Optional<Route> route = router.route(path);
    if (!route.isPresent()) {
      logger.infofmt("No action found for: %s", path);
      rsp.sendError(SC_NOT_FOUND);
      return;
    }
    if (!route.get().isMethodAllowed(method)) {
      logger.infofmt("Method %s not allowed for: %s", method, path);
      rsp.sendError(SC_METHOD_NOT_ALLOWED);
      return;
    }
    if (route.get().action().requireLogin() && !userService.isUserLoggedIn()) {
      logger.info("not logged in");
      rsp.setStatus(SC_MOVED_TEMPORARILY);
      rsp.setHeader(LOCATION, userService.createLoginURL(req.getRequestURI()));
      return;
    }
    if (route.get().shouldXsrfProtect(method)
        && !validateToken(
                nullToEmpty(req.getHeader(X_CSRF_TOKEN)),
                route.get().action().xsrfScope(),
                XSRF_VALIDITY)) {
      rsp.sendError(SC_FORBIDDEN, "Invalid " + X_CSRF_TOKEN);
      return;
    }
    // Build a new request component using any modules we've constructed by this point.
    C component = requestComponentBuilderProvider.get()
        .requestModule(new RequestModule(req, rsp))
        .build();
    // Apply the selected Route to the component to produce an Action instance, and run it.
    try {
      route.get().instantiator().apply(component).run();
      if (route.get().action().automaticallyPrintOk()) {
        rsp.setContentType(PLAIN_TEXT_UTF_8.toString());
        rsp.getWriter().write("OK\n");
      }
    } catch (HttpException e) {
      e.send(rsp);
    }
  }