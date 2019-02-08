# Interview test exercise


Pairs of LocalDateTime and Callable are sent to our application. We should execute Callable at exactly LocalDateTime. If it is not possible, it should be executed as soon as possible. If two tasks should be executed at the same time, they should be executed in order of incoming. Pairs may come in different threads simultaneously.

This solution is built on the top of DelayQueue which can hide elements for some time and show them only when their specified time comes.
