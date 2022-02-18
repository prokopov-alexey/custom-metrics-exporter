package ap.cme.metrics;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class MetricsLocator {
    @Autowired
    private ApplicationContext context;
    
    public IMetricsService getMetricsService(String version, String namespace, String service, String metricsName)
    {
        Map<String, Object> availableServices = context.getBeansWithAnnotation(MetricsService.class);
        
        for (Map.Entry<String, Object> pair: availableServices.entrySet()) {
            if (pair.getValue() instanceof IMetricsService) {
                IMetricsService metricsService = (IMetricsService) pair.getValue();
                if (
                    metricsService.getVersion().equals(version) && 
                    metricsService.getNamespace().equals(namespace) && 
                    metricsService.getServiceName().equals(service) && 
                    metricsService.getName().equals(metricsName)
                ) {
                    return metricsService;
                }
            }
        }
        
        return null;
    }
}
