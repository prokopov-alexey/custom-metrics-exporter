package ap.cme;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuartzMetricCollector {

    private final JdbcTemplate jdbcTemplate;

    public int queueSize() {

        return jdbcTemplate.query("SELECT count(*) from qrtz_triggers " +
                        "where  trigger_state='WAITING' and to_timestamp(next_fire_time/1000)<=CURRENT_TIMESTAMP",
                (rs, rowNum) -> rs.getInt(1))
                .get(0);

    }
}
