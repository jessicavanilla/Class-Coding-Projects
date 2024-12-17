# algorithm function
def bellman_ford(graph, source): 
    # helper function 
    def shortest_path(source, destination, cost):
        if distances[source] != float('inf') and distances[source] + cost < distances[destination]:
                distances[destination] = distances[source] + cost
      
    # create a list of all node names
    nodes = []
    for node in graph:
        nodes.append(node[0])
        nodes.append(node[1])
        
    # initialize dictionary with nodes as keys and distances (from source to all other nodes) as values
    distances = {node: float('inf') for node in nodes}
    distances[source] = 0

    # calculate the shortest path from source node to all other nodes
        # make sure to calculate for both the first value of the tuple and the second value because graph is bidirectional
    for _ in range(len(graph) - 1):
        for source, destination, cost in graph:
            shortest_path(source, destination, cost)
            shortest_path(destination, source, cost)

    # check for negative-weight cycles by traversing through one more time
    for source, destination, cost in graph:
        if distances[source] != float('inf') and distances[source] + cost < distances[destination]:
            return "Graph contains negative-weight cycle(s)"

    return distances

# example use case: small graph
smallgraph = [
    (1, 2, 2),
    (1, 3, 7),
    (2, 3, 1),
]

# example use case: negative graph
negativegraph = [
    (1, 2, -2),
    (1, 3, 1),
    (2, 3, -2),
]
# example use case: big graph
biggraph = [
    (1, 2, 3),
    (1, 3, 5),
    (2, 4, 6),
    (3, 4, 4),
    (2, 5, 7), 
    (5, 4, 2)
]

# printing answer
for source in range(3): 
    result = bellman_ford(smallgraph, source + 1)
    if type(result) == str:
        print(result)
    else:
        print(list(result.values()))

for source in range(3): 
    result = bellman_ford(negativegraph, source + 1)
    if type(result) == str:
        print(result)
    else:
        print(list(result.values()))
        
for source in range(5): 
    result = bellman_ford(biggraph, source + 1)
    if type(result) == str:
        print(result)
    else:
        print(list(result.values()))