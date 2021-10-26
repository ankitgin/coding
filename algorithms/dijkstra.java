/***

Questions: Implement dijkstra
You are given a network of n nodes, labeled from 1 to n. 
You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, 
and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. 
If it is impossible for all the n nodes to receive the signal, return -1.

Source: https://leetcode.com/problems/network-delay-time/
***/


class Solution {
    public int dijkstra(int[][] times, int n, int k) {
        
        //create weighted graph
        Map <Integer, List<int[]>> graph = new HashMap<>();
        for(int[] edge: times){
            int src = edge[0];
            int dest = edge[1];
            int w = edge[2];
            if(!graph.containsKey(src))
                graph.put(src, new ArrayList<>());
            graph.get(src).add(new int[]{dest,w});
        }
        
        //Apply Dijkstra's
        Map <Integer,Integer> distance = new HashMap<>();
        Set <Integer> visited = new HashSet<>();
        distance.put(k,0);
        PriorityQueue <int[]> heap = new PriorityQueue<>((a,b)->{
            return Integer.compare(a[1],b[1]);
        });
        heap.add(new int[]{k,0});
        while(!heap.isEmpty()){
            int[] temp = heap.poll();
            int u = temp[0];
            int d1 = temp[1];       //distance to reach to this node
            if(visited.contains(u)) continue;
            
            if(graph.containsKey(u)){
                for(int[] edge: graph.get(u)){
                    int v = edge[0];
                    int d2 = edge[1];
                    if(visited.contains(v)) continue;
                    int dist =0;
                    if(distance.containsKey(v))
                        dist = Math.min(distance.get(v), d1+d2);
                    else
                        dist = d1 +d2;
                    distance.put(v, dist);
                    heap.add(new int[]{v,dist});
                }
            }
            visited.add(u);
        }
        if(visited.size()!=n)   return -1;
        
        int ans =0;
        for(int key: distance.keySet()){
            if(key!=k)
                ans = Math.max(ans, distance.get(key));
        }
        return ans;
    }
}
