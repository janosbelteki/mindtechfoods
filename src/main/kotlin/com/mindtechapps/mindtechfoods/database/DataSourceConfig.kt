package com.mindtechapps.com.mindtechapps.mindtechfoods.database

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    fun dataSource(): DataSource {
        val dataSourceBuilder = DataSourceBuilder.create()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://localhost:5432/mindtechfoodsDB")
            .username("postgres")
            .password("postgres")
        return dataSourceBuilder.build()
    }
}