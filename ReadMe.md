# Load Balancer
### Test assignment for Microsoft

Prerequisites:

1. Java 8 JDK must be installed
2. Maven must be installed


This program depends on 3rd party libraries. The maven command (see below) will 
download and package those libraries along with the application binaries.
Locations of task and node input files are configured in the application.properties file.
The loadbalanser activity is logged into the output file. Location of the output file is also configured in the application.properties file.
Load balancer shuts down after all tasks are processed.
## Build
```batch
$ cd <project root>
$ mvn clean package appassembler:assemble
```

## Run
```batch
$ <project root>/target/appassembler/bin/lb
```
