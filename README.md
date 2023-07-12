# Kafka Connect JDBC Connector

kafka-connect-jdbc is a [Kafka Connector](http://kafka.apache.org/documentation.html#connect)
for loading data to and from any JDBC-compatible database.

Documentation for this connector can be found [here](http://docs.confluent.io/current/connect/connect-jdbc/docs/index.html).

# Development

To build a development version you'll need a recent version of Kafka as well as a set of upstream Confluent projects, which you'll have to build from their appropriate snapshot branch. See the [FAQ](https://github.com/confluentinc/kafka-connect-jdbc/wiki/FAQ)
for guidance on this process.

You can build kafka-connect-jdbc with Maven using the standard lifecycle phases.

# FAQ

Refer frequently asked questions on Kafka Connect JDBC here -
https://github.com/confluentinc/kafka-connect-jdbc/wiki/FAQ

# Contribute

Contributions can only be accepted if they contain appropriate testing. For example, adding a new dialect of JDBC will require an integration test.

- Source Code: https://github.com/confluentinc/kafka-connect-jdbc
- Issue Tracker: https://github.com/confluentinc/kafka-connect-jdbc/issues
- Learn how to work with the connector's source code by reading our [Development and Contribution guidelines](CONTRIBUTING.md).

# Information

For more information, check the documentation for the JDBC connector on the [confluent.io](https://docs.confluent.io/current/connect/kafka-connect-jdbc/index.html) website. Questions related to the connector can be asked on [Community Slack](https://launchpass.com/confluentcommunity) or the [Confluent Platform Google Group](https://groups.google.com/forum/#!topic/confluent-platform/).

# Change tracking mode
here is an example how to use change tracking mode
```
{
  "name": "jdbc_source_mssql_01",
  "config": {
    "connector.class": "io.confluent.connect.jdbc.JdbcSourceConnector",
    "connection.url": "jdbc:sqlserver://mssql:1431;databaseName=test",
    "connection.user": "connect_user",
    "connection.password": "connect_password",
    "topic.prefix": "mssql-01-",
    "poll.interval.ms" : 5000,
    "table.whitelist" : "dbo.accounts",
    "mode":"changetracking"
  }
}
```
As prerequisites, change tracking feature should be enabled on MSSQL:
```
USE [test];
ALTER DATABASE [test] SET CHANGE_TRACKING = ON;
ALTER TABLE [dbo].[accounts] ENABLE CHANGE_TRACKING;
GRANT VIEW CHANGE TRACKING ON [dbo].[accounts] TO connect_user;

```




# License

This project is licensed under the [Confluent Community License](LICENSE).

