package org.example;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ninja_squad.dbsetup.Operations.*;

class DbUnitTest {

    JdbcDataSource dataSource = null;

    @BeforeEach
    void setUp() {
        dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:tcp://localhost:9123/mem:mydb");
        dataSource.setUser("h2db");
        dataSource.setPassword("h2db");
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testDb() {
        Operation operation =
                sequenceOf(
                        deleteAllFrom("users"),
                        insertInto("users")
                                .columns("name")
                                .values("ABC")
                                .values("XYZ")
                                .build());
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
    }
}
