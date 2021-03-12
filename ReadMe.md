# Load Balancer Task
### Test assignment for Microsoft

Prerequisites:

1. Java 8 JDK must be installed
2. Maven must be installed


This utility depends on 3rd party libraries. The maven command (see below) will 
download and package those libraries along with the application binaries.
## Build
```batch
$ cd <project root>
$ mvn clean package appassembler:assemble
```

## Run
```batch
$ <project root>/target/appassembler/bin/bigid
```
