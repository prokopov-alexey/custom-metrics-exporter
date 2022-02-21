package ap.cme.metrics;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class MetricsLocator {

    @Autowired
    private List<IMetricsService> metricsServices;

    public IMetricsService getMetricsService(String version, String namespace, String service, String metricsName) {
        for (IMetricsService metricsService : metricsServices) {
            if (metricsService.getVersion().equals(version)
                && metricsService.getNamespace().equals(namespace)
                && metricsService.getServiceName().equals(service)
                && metricsService.getName().equals(metricsName)) {
                return metricsService;
            }
        }

        return null;
    }
}
