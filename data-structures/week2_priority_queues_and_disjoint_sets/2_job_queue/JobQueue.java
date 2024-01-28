import java.io.*;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        PriorityQueue<ThreadInWork> jobsInProcess = new PriorityQueue<>();

        int m = numWorkers;
        if (numWorkers > jobs.length)
            m = jobs.length;

        for (int i = 0; i < m; i++) {
            jobsInProcess.add(new ThreadInWork(i, i, (long) jobs[i], 0L));
            assignedWorker[i] = i;
            startTime[i] = 0;
        }
        for (int i = m; i < jobs.length; i++) {
            ThreadInWork threadFree = jobsInProcess.poll();
            jobsInProcess.add(new ThreadInWork(threadFree.threadId, i, (long) jobs[i], threadFree.endTime));
            assignedWorker[i] = threadFree.threadId;
            startTime[i] = threadFree.endTime;
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class ThreadInWork implements Comparable<ThreadInWork> {
        Integer threadId;
        Long jobTime;
        Integer jobId;
        Long startTime;

        Long endTime;

        public ThreadInWork(Integer threadId, Integer jobId, Long jobTime, Long startTime) {
            this.threadId = threadId;
            this.jobTime = jobTime;
            this.jobId = jobId;
            this.startTime = startTime;
            this.endTime = this.jobTime + this.startTime;
        }

        @Override
        public int compareTo(ThreadInWork o) {
            if(!Objects.equals(this.endTime, o.endTime))
            return this.endTime.compareTo(o.endTime);
            else
                return this.threadId.compareTo(o.threadId);
        }
    }

    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
