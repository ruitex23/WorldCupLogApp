/**
 * 
 */
package config;


import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author ruitex23
 *
 */
@Configuration
@EnableTransactionManagement
public class WorldCupLogDatabaseTestConfiguration extends Neo4jConfiguration {

//
//	public static final String URL_MAC = "file:///Users/ruitex23/Documents/Trabalho/databases/worldcuplog.graphdb";
//	public static final String URL_WIN = "file:///C:/WorldCupDatabase";
//	public static final File url = new File("\\databases\\worldcuplog.graphdb");
	public static final String URL_BOLT = "bolt://bert:123@localhost";

	@Bean
	public org.neo4j.ogm.config.Configuration getConfiguration() {
		org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
//		if(WorldCupUtils.detectOS().compareToIgnoreCase("mac") == 0) {
//			config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver")
//			.setURI(URL_MAC);
//			System.out.println("I'm a Mac! :D");
//		} else { 
//			config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver")
//			.setURI(URL_WIN);
//			System.out.println("I'm Windows :(");
//		}
		config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
		.setURI(URL_BOLT);
		System.out.println("setted the driver");
		return config;
	}

	@Override
	public SessionFactory getSessionFactory() {
		System.out.println("getSessionFactory");
		return new SessionFactory(getConfiguration(), "domains");
	}

}
