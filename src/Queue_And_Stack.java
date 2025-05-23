import java.util.PriorityQueue;
import java.util.Comparator;

public class Queue_And_Stack {
    static class Element {
        int value;
        int time;

        Element(int value, int time) {
            this.value = value;
            this.time = time;
        }
    }

    static class PriorityQueueStack {
        private int timestamp = 0;
        private PriorityQueue<Element> stack;

        public PriorityQueueStack() {
            stack = new PriorityQueue<>(Comparator.<Element>comparingInt(e -> -e.time));
        }

        public void push(int value) {
            stack.offer(new Element(value, timestamp++));
        }

        public int pop() {
            return stack.poll().value;
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    static class PriorityQueueQueue {
        private int timestamp = 0;
        private PriorityQueue<Element> queue;

        public PriorityQueueQueue() {
            queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.time));
        }

        public void enqueue(int value) {
            queue.offer(new Element(value, timestamp++));
        }

        public int dequeue() {
            return queue.poll().value;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        // Stack simulation
        System.out.println("=== Stack ===");
        PriorityQueueStack stack = new PriorityQueueStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        while (!stack.isEmpty()) {
            System.out.println("Pop: " + stack.pop());
        }

        // Queue simulation
        System.out.println("\n=== Queue ===");
        PriorityQueueQueue queue = new PriorityQueueQueue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        while (!queue.isEmpty()) {
            System.out.println("Dequeue: " + queue.dequeue());
        }
    }
}