static boolean badNode(List<ServiceInstance> services) {
        Map<ServiceStatus, Long> countsByStatus = services.stream()
                .collect(Collectors.groupingBy(ServiceInstance::serviceStatus, counting()));

        return countsByStatus.getOrDefault(ServiceStatus.UP, 0L) <= 0L &&
                countsByStatus.getOrDefault(ServiceStatus.DOWN, 0L) > 0L;
    }