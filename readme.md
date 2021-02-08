Java Memory Leaks
--
This repository is the result of preparation for internal talk in [Intrum Global Technologies](https://intrum.com)
to share my knowledge about how the memory leaks are working, what the usual cases for them and how
to spot & fix them.

Talk Agenda
---
* **What's a memory leak?**
    * Java GC under the hood
    * Passing object by reference 
* **The common leak cases**
    * Overload buffers - [Zoe DEMO](src/main/java/memory/MemoryEaterZoe.java)
    * Unclosed connections / buffers
    * Hidden infinite cycle - [Bart DEMO](src/main/java/memory/MemoryEaterBart.java)
    * Anonymous class context memory leak - [Evelyn DEMO](src/main/java/memory/MemoryEaterEvelyn.java)
* **Tools to debug**
    * Visual VM
    * Eclipse Memory Analyzer
* **Questions**

Suspect a leak?
---
* Check amount of free memory after GC right after app startup and some time after
* Try to get HEAP dump and analyze it
* Try to figure out what is allocated in memory for your application
* Remember that memory leak not always looks like a memory leak, but usually as 
  an overload CPU (because of infinite GC) 

Run details
---
```
java \
   -verbose:gc \
   -Xmx25M \
   -Dcom.sun.management.jmxremote \
   -Dcom.sun.management.jmxremote.port=9010 \
   -Dcom.sun.management.jmxremote.local.only=false \
   -Dcom.sun.management.jmxremote.authenticate=false \
   -Dcom.sun.management.jmxremote.ssl=false \
memory.MemoryEaterBart
```

Tools
---
* Visual VM - https://visualvm.github.io/
* Eclipse Memory Analyzer - https://www.eclipse.org/mat/
* Other tools listed here - https://stackify.com/java-memory-leaks-solutions/
