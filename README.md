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
