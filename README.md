# onDuplicateKeyUpdate

See the comments below [Find or insert based on unique key with Hibernate](https://stackoverflow.com/questions/5022812/find-or-insert-based-on-unique-key-with-hibernate/47095145#47095145)
on Stack Overflow. I tried the solution proposed by Vlad Mihalcea, but I think
the "find part" is not really working this way, at least if MySQL 5.5.x is used.

* create a database 'demo' with user 'demo' and password 'demo', see `src/test/resources/application.properties`

* run all the tests (there is only one ;-)) with: `mvn clean test`

* the test should fail with some output similar to the following:

<pre>
    Thread 2: no bean found by findByName("dummy")
Thread 1: no bean found by findByName("dummy")
    Thread 2: numChanged: 1
    Thread 2: bean was created with id: 15
    Thread 2: found bean by findOne(15)
Thread 1: numChanged: 1
Thread 1: bean was created with id: 15
Thread 1: no bean found by findOne(15)
Thread 1: no bean found by findByName("dummy")
Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 3.794 sec
<<< FAILURE! - in com.example.demo.OnDuplicateKeyUpdateTests
testConcurrentFindOrCreate(com.example.demo.OnDuplicateKeyUpdateTests)
 Time elapsed: 0.42 sec  <<< FAILURE!
java.lang.AssertionError: 
Expected size:<2> but was:<1> in: [...]
</pre>

# Explanation

The reason why it's not working for you is because every `Bean` row will have its own unique identifier. For this to work, you need to materialize the conflict on the same value.

Hence, either the rows that you want to insert have the same Primary Key, as [explained in my article](https://vladmihalcea.com/2017/11/06/how-do-upsert-and-merge-work-in-oracle-sql-server-postgresql-and-mysql/), or the records you want to insert have different Primary Keys, but then you need to have a UNIQUE column with the same value shared between the two records you want to insert.

MERGE and UPSERT only work if you suspply the same filtering criteria, hence the same Primary Key or Unique Key value.
