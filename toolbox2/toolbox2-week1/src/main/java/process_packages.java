import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class Request {
    public Request(int arrival_time, int process_time) {
        this.arrivalTime = arrival_time;
        this.processTime = process_time;
    }

    public int arrivalTime;
    public int processTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;

        Request request = (Request) o;

        if (arrivalTime != request.arrivalTime) return false;
        return processTime == request.processTime;
    }

    @Override
    public int hashCode() {
        int result = arrivalTime;
        result = 31 * result + processTime;
        return result;
    }
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }

    public boolean dropped;
    public int start_time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response)) return false;

        Response response = (Response) o;

        if (dropped != response.dropped) return false;
        return start_time == response.start_time;
    }

    @Override
    public int hashCode() {
        int result = (dropped ? 1 : 0);
        result = 31 * result + start_time;
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "dropped=" + dropped +
                ", start_time=" + start_time +
                '}';
    }
}

class Buffer {
    public Buffer(int size) {
        this.size = size;
        this.finishTime = new LinkedList<>();
    }

    public Response Process(Request request) {

        while (!finishTime.isEmpty()) {
            int current = finishTime.getFirst();
            if (current <= request.arrivalTime) {
                finishTime.removeFirst();
                continue;
            }
            break;
        }

        if (finishTime.isEmpty()) {
            finishTime.add(request.arrivalTime + request.processTime);
            return new Response(false, request.arrivalTime);
        }

        if (finishTime.size() >= size) {
            return new Response(true, 0);
        } else {
            Integer last = finishTime.getLast();
            finishTime.add(last + request.processTime);
            return new Response(false, last);
        }
    }

    private int size;
    private LinkedList<Integer> finishTime;
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

    static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
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
