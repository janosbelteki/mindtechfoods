package com.mindtechapps.com.mindtechapps.mindtechfoods.database

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.sql.Connection
import java.sql.DriverManager
import javax.annotation.PostConstruct

@Component
class DataInitializer {

    @PostConstruct
    @Throws(Exception::class)
    fun initializeData() {
        val connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mindtechfoodsDB", "postgres", "postgres")
        connection.autoCommit = true
        createTablesIfNotExists(connection)
    }

    @Throws(Exception::class)
    fun createTablesIfNotExists(connection: Connection): Int {
        createCustomerTable(connection)

        val sql = "SELECT COUNT(*) FROM CUSTOMERS"
        val statement = connection.createStatement()


        val resultSet = statement.executeQuery(sql)
        var rowCount = 0
        if (resultSet.next()) {
            rowCount = resultSet.getInt(1)
        }
        return rowCount
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(DataInitializer::class.java)

        @Throws(Exception::class)
        private fun createCustomerTable(connection: Connection) {
            val createTableQuery = ("CREATE TABLE IF NOT EXISTS CUSTOMERS" +
                    " (" +
                    "id SERIAL PRIMARY KEY, " +
                    "email VARCHAR(255), " +
                    "password_hash VARCHAR(255) NOT NULL, " +
                    "name VARCHAR(255), " +
                    "created_at VARCHAR(255), " +
                    "address VARCHAR(255) " +
                    ")")
            val statement = connection.prepareStatement(createTableQuery)
            statement.execute()
            statement.close()
        }

        @Throws(Exception::class)
        private fun createRestaurantTable(connection: Connection) {
            val createTableQuery = ("CREATE TABLE IF NOT EXISTS RESTAURANTS" +
                    " (" +
                    "id SERIAL PRIMARY KEY, " +
                    "contact VARCHAR(255), " +
                    "name VARCHAR(255), " +
                    "created_at VARCHAR(255), " +
                    "address VARCHAR(255), " +
                    "menu VARCHAR(255) " +
                    ")")
            //TODO: itt is majd a menüt bővíteni kell foreign key mappinggal ha object lesz
            val statement = connection.prepareStatement(createTableQuery)
            statement.execute()
            statement.close()
        }

        /*@Throws(Exception::class)
        private fun createOrdersTable(connection: Connection) {
            val createTableQuery = ("CREATE TABLE IF NOT EXISTS ORDERS" +
                    " (" +
                    "id SERIAL PRIMARY KEY, " +
                    "FOREIGN KEY (customer_id) REFERENCES CUSTOMERS " + "(id)" +
                    "FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS " + "(id)" +
                    "created_at VARCHAR(255), " +
                    // TODO: ide majd az itemst fel kell venni, illetve azoknak egy külön táblát is esetleg
                    ")")
            val statement = connection.prepareStatement(createTableQuery)
            statement.execute()
            statement.close()
        }*/
    }
}
