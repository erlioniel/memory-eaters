Agenda:
---
* What's memory leak?
    * GC under the hood
    * Object by reference 
* The common leak cases
    * Overload buffers (Zoe DEMO)
    * Unclosed connections / buffers
    * Hidden infinite cycle (Bart DEMO)
    * Anonymous class context memory leak (Evelyn DEMO)
* Tools to debug
    * Visual VM
    * Eclipse Memory Analyzer
* Real case (?)
* Questions

Suspect a leak?
---
* Check amount of free memory after GC right after app startup and some time after
* Try to get HEAP dump and analyze it
* Try to figure out what is allocated in memory for your application
* Remember that memory leak not always looks like a memory leak, but usually as 
  an overload CPU (because of infinite GC) 

Tools:
---
* Visual VM
* Eclipse Memory Analyzer
* Other tools listed here - https://stackify.com/java-memory-leaks-solutions/