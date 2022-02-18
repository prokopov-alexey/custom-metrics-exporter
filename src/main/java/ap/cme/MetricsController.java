package ap.cme;

import ap.cme.dto.ApiResourceList;
import ap.cme.dto.Metadata;
import ap.cme.dto.DescribedObject;
import ap.cme.dto.MetricsObject;
import ap.cme.dto.MetricsResponse;
import ap.cme.dto.Resource;
import ap.cme.dto.StatusResponse;
import ap.cme.metrics.IMetricsService;
import ap.cme.metrics.MetricsLocator;
import ap.cme.metrics.PointsMetricsService;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MetricsController {

    private IMetricsService pointMetricService;

    @Autowired
    private MetricsLocator locator;

    private String getTimestamp() {
        ZonedDateTime date = ZonedDateTime.now();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendInstant(0).toFormatter();

        return date.format(formatter);
    }

    @GetMapping("/apis/custom.metrics.k8s.io/{version}")
    public ApiResourceList getResourceList(@PathVariable String version) {
        ApiResourceList response = new ApiResourceList(version, "custom.metrics.k8s.io");
        Resource points = new Resource("MetricValueList", "points", "point", true);
        points.getVerbs().add("get");

        response.getResources().add(points);

        return response;
    }

    @GetMapping("/apis/custom.metrics.k8s.io/{version}/livez")
    public StatusResponse getStatus(@PathVariable String version) {
        return new StatusResponse(StatusResponse.STATUS_HEALTHY);
    }

    @GetMapping("/apis/custom.metrics.k8s.io/{version}/namespaces/{namespace}/services/{service}/{metricsName}")
    public MetricsResponse getPoints(
        @PathVariable String version,
        @PathVariable String namespace,
        @PathVariable String service,
        @PathVariable String metricsName
    ) {

        pointMetricService = locator.getMetricsService(version, namespace, service, metricsName);
        
        if (pointMetricService == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
            );
        }

        MetricsResponse response = new MetricsResponse(
            "MetricValueList",
            "custom.metrics.k8s.io/" + version,
            new Metadata("/apis/custom.metrics.k8s.io/" + version)
        );
        
        response.getItems().add(
            new MetricsObject(
                pointMetricService.getName(),
                getTimestamp(),
                pointMetricService.getValue(),
                new DescribedObject("Service", namespace, service, "/" + version)
            )
        );

        return response;
    }
}
