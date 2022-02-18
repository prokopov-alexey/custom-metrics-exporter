package ap.cme.metrics;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@MetricsService
@Lazy
@Scope("singleton")
public class PointsMetricsService implements IMetricsService {

    @Override
    public String getVersion() {
        return "v1beta1";
    }

    @Override
    public String getNamespace() {
        return "default";
    }

    @Override
    public String getServiceName() {
        return "metrics-exporter";
    }

    @Override
    public String getName() {
        return "points";
    }

    @Override
    public String getValue() {
        return "20";
    }
}
