# ibm-ponder-this-stats

This project provides APIs for extracting various statistics about the [IBM Ponder This](https://research.ibm.com/haifa/ponderthis/index.shtml) challenges.

# building and running

Java 17, Gradle 8.5 is needed.
```commandline
gradle clean build
```
Once you build the project successfully, you can check and run `demo.Demo` class.

# usage
There are 2 classes which expose the query API:
```
core.query.OnlineQueryEngine
core.query.OfflineQueryEngine
```
The first call to ```OnlineQueryEngine#compilerResults``` will spawn multiple threads. 
Each of those threads will fetch and compile the contents of the webpage of a particular challenge.  
Those compiler threads will run in parallel using a thread pool and their results will be merged once all of them are completed.  
You may see logging messages about the challenges which don't have any published solvers or the challenges which don't exist. That's fine.  
Subsequent calls to ```OnlineQueryEngine#compilerResults``` within the same Java process will not repeat the compilation process described above.  
Instead, the compilation results gathered from the first call will be reused. Before using ```OfflineQueryEngine```, you've to run the following:  
```
OnlineQueryEngine.getInstance().flushToFileSystem();
```
After that, you can use OfflineQueryEngine which will load the challenges from the files serialized by the call to ```flushToFileSystem```.

# examples

```java
        OfflineQueryEngine offlineQueryEngine = OfflineQueryEngine.getInstance();
        List<Challenge> challenges = offlineQueryEngine.allChallenges().stream()
                .filter(challenge -> challenge.getYear() >= CURRENT_YEAR - 7)
                .toList();
        List<Ponderer> ponderers = offlineQueryEngine.ponderersOf(challenges);
        Demo.reportPonderers(ponderers, 111);
```
The code snippet above will display a table listing the top 111 ponderers of the last 8 calendar years.
```
┌──────────────────────────┬─────────────────────────┬─────────────────────────┐
│RANK                      │PONDERER                 │CHALLENGES SOLVED        │
├──────────────────────────┼─────────────────────────┼─────────────────────────┤
│1                         │David Greer              │92                       │
├──────────────────────────┼─────────────────────────┼─────────────────────────┤
│2                         │Alper Halbutogullari     │89                       │
├──────────────────────────┼─────────────────────────┼─────────────────────────┤
│3                         │Bert Dobbelaere          │84                       │
├──────────────────────────┼─────────────────────────┼─────────────────────────┤
                                 ...
├──────────────────────────┼─────────────────────────┼─────────────────────────┤
│110                       │Erik Hostens             │14                       │
├──────────────────────────┼─────────────────────────┼─────────────────────────┤
│111                       │Fakih Karademir          │14                       │
├──────────────────────────┼─────────────────────────┼─────────────────────────┤
```