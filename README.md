# EvolutionaryGraphs
This was a university project that attempted to solve a mobility problem in my city: How can bus routes change dinamically responding to traffic?

The answer we gave was scalable and simple: If we have an constant input of TIME BETWEEN BUS STOPS we will be able to measure traffic. Then we can use those values and implement a method of Statistical Learning to predict traffic jams at specific hours, days, etc.
Finally, we can model the city as a graph and use an algorithm for the All-Pairs-Shortest-Path problem to return different routes for different hours of the day.

If you want to clone the project remember to edit the Run Configurations to make it start from RoutingAgents.
