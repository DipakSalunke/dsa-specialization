import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Request {
    public int arrival_time;
    public int process_time;

    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }
}

class Response {
    public boolean dropped;
    public int start_time;

    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }
}

class Buffer {
    private final int size_;
    private final ArrayList<Integer> finish_time_;

    public Buffer(int size) {
        this.size_ = size;
        this.finish_time_ = new ArrayList<Integer>();
    }

    public Response Process(Request request) {
        int startTime = 0;

        while(!finish_time_.isEmpty() && this.finish_time_.get(0) <=request.arrival_time)
            this.finish_time_.remove(0);

        if (finish_time_.size() < size_ && !finish_time_.isEmpty()) {
            startTime = max(finish_time_.get(finish_time_.size() - 1), request.arrival_time);
            this.finish_time_.add(startTime + request.process_time);
        }
        else if(finish_time_.isEmpty()){
            startTime = request.arrival_time;
            this.finish_time_.add(startTime + request.process_time);
        }
         else {
            return new Response(true, -1);
        }
        return new Response(false,startTime);
    }

    private int max(Integer integer, int arrivalTime) {
        if(integer>arrivalTime) return integer; else return arrivalTime;
    }
}

class process_packages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
