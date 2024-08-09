public ServerBuilder decorator(
            DecoratingHttpServiceFunction decoratingHttpServiceFunction) {
        return decorator(Route.builder().catchAll().build(), decoratingHttpServiceFunction);
    }