
package com.wownetwork.todolist.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;
import org.springframework.cloud.aws.jdbc.config.annotation.RdsInstanceConfigurer;
import org.springframework.cloud.aws.jdbc.datasource.DataSourceFactory;
import org.springframework.cloud.aws.jdbc.datasource.TomcatJdbcDataSourceFactory;
import org.springframework.cloud.aws.jdbc.datasource.support.DatabasePlatformSupport;
import org.springframework.cloud.aws.jdbc.datasource.support.DatabaseType;
import org.springframework.cloud.aws.jdbc.datasource.support.MapBasedDatabasePlatformSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*

@EnableRdsInstance(
	dbInstanceIdentifier = "${CLOUD_AWS_RDS_ID}",
	databaseName = "${CLOUD_AWS_RDS_DATABASE_NAME}",
	username = "${CLOUD_AWS_RDS_USERNAME}",
	password = "${CLOUD_AWS_RDS_PASSWORD}")

@EnableRdsInstance(
	dbInstanceIdentifier = "${db-instance-identifier:}",
	databaseName = "${database-name:}", 
	password = "${rdsPassword:}")

 */

/**
 *
 */
@Configuration
@EnableRdsInstance(dbInstanceIdentifier = "${CLOUD_AWS_RDS_ID}", databaseName = "${CLOUD_AWS_RDS_DATABASE_NAME}", username = "${CLOUD_AWS_RDS_USERNAME}", password = "${CLOUD_AWS_RDS_PASSWORD}")
public class AwsResourceConfiguration {

	// The logger
	private static final Logger logger = LoggerFactory.getLogger(AwsResourceConfiguration.class);

	/**
	 * @return
	 */
	@Bean
	public RdsInstanceConfigurer instanceConfigurer() {

		return new RdsInstanceConfigurer() {

			@Override
			public DataSourceFactory getDataSourceFactory() {

				logger.info("Custumize Tomcat jdbc data source factory...");

				TomcatJdbcDataSourceFactory dataSourceFactory = new TomcatJdbcDataSourceFactory();
				DatabasePlatformSupport databasePlatformSupport = new MapBasedDatabasePlatformSupport() {

					@Override
					protected Map<DatabaseType, String> getDriverClassNameMappings() {
						HashMap<DatabaseType, String> driverClassNameMappings = new HashMap<>();
						driverClassNameMappings.put(DatabaseType.MYSQL, "com.mysql.cj.jdbc.Driver");
						return Collections.unmodifiableMap(driverClassNameMappings);
					}

					@Override
					protected Map<DatabaseType, String> getSchemeNames() {
						HashMap<DatabaseType, String> schemeNamesMappings = new HashMap<>();
						schemeNamesMappings.put(DatabaseType.MYSQL, "jdbc:mysql");
						return Collections.unmodifiableMap(schemeNamesMappings);
					}

					@Override
					protected Map<DatabaseType, String> getAuthenticationInfo() {
						return Collections.singletonMap(DatabaseType.ORACLE, "@");
					}
				};
				dataSourceFactory.setDatabasePlatformSupport(databasePlatformSupport);

				dataSourceFactory.setTestWhileIdle(true);
				dataSourceFactory.setTestOnBorrow(true);
				dataSourceFactory.setValidationQuery("SELECT 1");
				String connectionProperties = "useSSL=false";
				dataSourceFactory.setConnectionProperties(connectionProperties);

				return dataSourceFactory;
			}
		};

	}

}
