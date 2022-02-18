package ap.cme.metrics;

import ap.cme.QuartzMetricCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@MetricsService
@Lazy
@Scope("singleton")
@RequiredArgsConstructor
public class QuartzMetricsService implements IMetricsService {

    private final QuartzMetricCollector quartzMetricCollector;

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
        return "quartz-queue";
    }

    @Override
    public String getValue() {
        return String.valueOf(quartzMetricCollector.queueSize());
    }
}
