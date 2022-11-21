package com.migros.courier.util;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class DbInitializer {

    private final DataSource dataSource;

  //  @EventListener(ApplicationReadyEvent.class)
    void initializeValues() {
        var resource = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("static/data.sql"));
        resource.execute(dataSource);
    }
}
