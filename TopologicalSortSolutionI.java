/* You're given a list of arbitrary jobs that need to be completed; these jobs are represented by
distinct integers. You're also given a list of dependencies. A dependency is represented as a pair of jobs where
the first job is a prerequisite of the second one. In other words, the second job depends on the first one; it
can only be completed once the first job is completed. Write a function that takes in a list of jobs and a list
of dependencies and returns a list containing a valid order in which the given jobs can be completed. If no such
order exists, the function should return an empty array.
Sample Input:
jobs = [1, 2, 3, 4]
deps = [[1, 2], [1, 3], [3, 2], [4, 2], [4, 3]]
Sample Output:
[1, 4, 3, 2] or [4, 1, 3, 2]
Hint1 Try representing the jobs and dependencies as a graph, where each vertex is a job and each edge is a
dependency. How can you traverse this graph to topologically sort the list of jobs?
Hint2 One approach to solving this problem is to traverse the graph mentioned in Hint #1 using Depth-first Search.
Starting at a random job, traverse its prerequisite jobs in Depth-first Search fashion until you reach a job with
no prerequisites; such a job can safely be appended to the final order. Once you've traversed and added all
prerequisites of a job to the final order, you can append the job in question to the order. This approach will
have to track whether nodes have been traversed already, whether they're in the process of being traversed (which
would indicate a cycle in the graph and therefore no valid topological order), or whether they're ready to be
traversed.
Hint3 Another approach to solving this problem is to traverse the graph mentioned in Hint #1 starting specifically
with jobs that have no prerequisites. Keep track of all the jobs that have no prerequisites, traverse them one by
one, and append them to the final order. For all of these jobs, remove their dependencies from the graph and
update the number of prerequisites for each of these dependencies accordingly (these dependencies should now have
one prerequisite less since one of their prerequisite job has just been added to the final order). As you update
the number of prerequisites for these other jobs, keep track of the ones that no longer have prerequisites and
that are ready to be traversed. You'll eventually go through all of the jobs if there are no cycles in the graph.
If there is a cycle in the graph, there will still be jobs with prerequisites and you'll know that there is no
valid topological order. This approach will involve keeping track of the number of prerequisites per job as well
as all the actual dependencies of each job.

Optimal Space & Time Complexity - O(j+d) time | O(j+d) space - where j is the number of jobs and d is the number
of dependencies. */
package FamousAlgorithms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TopologicalSortSolutionI {
    public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
        JobGraph jobGraph = createJobGraph(jobs, deps);
        return getOrderedJobs(jobGraph);
    }
    public static JobGraph createJobGraph(List<Integer> jobs, List<Integer[]> deps) {
        JobGraph graph = new JobGraph(jobs);
        for(Integer[] dep: deps) {
            graph.addPrereq(dep[1], dep[0]);
        }
        return graph;
    }
    public static List<Integer> getOrderedJobs(JobGraph graph) {
        List<Integer> orderedJobs = new ArrayList<Integer>();
        List<JobNode> nodes = new ArrayList<JobNode>(graph.nodes);
        while (nodes.size() > 0) {
            JobNode node = nodes.get(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);
            boolean containsCycle = depthFirstTraverse(node, orderedJobs);
            if (containsCycle) return new ArrayList<Integer>();
        }
        return orderedJobs;
    }
    public static boolean depthFirstTraverse(JobNode node, List<Integer> orderedJobs) {
        if (node.visited) return false;
        if (node.visiting) return true;
        node.visiting = true;
        for (JobNode prereqNode : node.prereqs) {
            boolean containsCycle = depthFirstTraverse(prereqNode, orderedJobs);
            if(containsCycle) return true;
        }
        node.visited = true;
        node.visiting = false;
        orderedJobs.add(node.job);
        return false;
    }
    static class JobGraph {
        public List<JobNode> nodes;
        public Map<Integer, JobNode> graph;
        public JobGraph(List<Integer> jobs) {
            nodes = new ArrayList<JobNode>();
            graph = new HashMap<Integer, JobNode>();
            for (Integer job: jobs) {
                addNode(job);
            }
        }
        public void addPrereq(Integer job, Integer prereq) {
            JobNode jobNode = getNode(job);
            JobNode prereqNode = getNode(prereq);
            jobNode.prereqs.add(prereqNode);
        }
        public void addNode(Integer job) {
            graph.put(job, new JobNode(job));
            nodes.add(graph.get(job));
        }
        public JobNode getNode(Integer job) {
            if (!graph.containsKey(job)) addNode(job);
            return graph.get(job);
        }
    }
    static class JobNode{
        public Integer job;
        public List<JobNode> prereqs;
        public boolean visited;
        public boolean visiting;
        public JobNode(Integer job) {
            this.job = job;
            prereqs = new ArrayList<JobNode>();
            visited = false;
            visiting = false;
        }
    }
}
