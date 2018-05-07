# Console-based Social Networking Application

[![MIT License](https://img.shields.io/badge/license-MIT%20License-green.svg)][1]
[![Java Platform](https://img.shields.io/badge/platform-Java-blue.svg)][2]

Implementation of a console-based social networking application (similar to Twitter) satisfying the scenarios below.

- **Posting : Alice can publish messages to a personal timeline**

````shell
> Alice -> I love the weather today
> Bob -> Damn! We lost!
> Bob -> Good game though.

````

- **Reading : I can view Alice and Bob's timelines**

````shell
> Alice
I love the weather today (5 minutes ago)
> Bob
Good game though. (1 minute ago)
Damn! We lost! (2 minutes ago)

````

- **Following : Charlie can subscribe to Alice's and Bob's timelines, and view an aggregated list of all subscriptions**

````shell
> Charlie -> I'm in New York today! Anyone want to have a coffee?
> Charlie follows Alice
> Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
Alice - I love the weather today (5 minutes ago)

> Charlie follows Bob
> Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (15 seconds ago)
Bob -> Good game though. (1 minute ago)
Bob -> Damn! We lost! (2 minutes ago)
Alice - I love the weather today (5 minutes ago)

````


## Requirements

- Download and install [Java SE 8][3]
- Download and install [Maven 3.5.3][4]


## Setup and Run Instructions

- Download and unzip this `social-networking-application` project source code.
- From a command line window, navigate to the (newly unzipped) project root folder, then run the `mvn clean install` command.
- To launch the application, run `java -jar target/social-networking-application-1.0.0-SNAPSHOT.jar`.


## License

[The MIT License (MIT)][5]

````
Copyright (c) 2018 Solange Umuhire Gasengayire.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

````


[1]: https://github.com/SolangeUG/social-networking-application/blob/master/LICENSE
[2]: https://docs.oracle.com/en/java/
[3]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[4]: https://maven.apache.org/download.cgi
[5]: https://opensource.org/licenses/MIT