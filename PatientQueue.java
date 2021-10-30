
/*
 * Program name: PatientQueue.java
 * Author: Xin Li
 * Description: This is a program that manage patient orders
 * using priority queue.
 * 
 * enqueue - add the given person into the patient queue
 * dequeue - remove the frontmost patient
 * peek - return the name of the frontmost patient
 * peekPriority - return the priority of the frontmost patient
 * changePriority - modify the priority of a given existing patient
 * isEmpty - return true if the queue is empty
 * size - return the size of the queue
 * clear - remove all elements from the queue
 * toString - return the string representation of the queue
 * 
 */

public class PatientQueue {

    private Patient[] queue;
    private int index;
    private int capacity;
    private static final int MIN_CAPACITY = 10;

    /*
     * constructor of the PatientQueue, initialize
     * the queue, capacity, and index
     */
    public PatientQueue() {
        queue = new Patient[MIN_CAPACITY];
        capacity = MIN_CAPACITY;
        index = 1;
    }

    /*
     * add the given person into the patient queue
     * parameter: String name, integer priority
     * return: none
     */
    public void enqueue(String name, int priority) {
        Patient patient = new Patient(name, priority);
        if (index == capacity) {
            doubleSize();
        }
        queue[index] = patient;
        index++;
        bubbleUp(index);
    }

    /*
     * add the given person into the patient queue
     * parameter: Patient patient
     * return: none
     */
    public void enqueue(Patient patient) {
        if (index == capacity) {
            doubleSize();
        }
        queue[index] = patient;
        index++;
        bubbleUp(index);
    }

    /*
     * double the array size if it's full
     * parameter: none
     * return: none
     */
    private void doubleSize() {
        Patient[] temp = queue.clone();
        queue = new Patient[capacity * 2];
        for (int i = 1; i < temp.length; i++) {
            queue[i] = temp[i];
        }
    }

    /*
     * bubble up the element if it has high priority
     * parameter: integer position
     * return: none
     */
    private void bubbleUp(int position) {
        int parent = (index - 1) / 2;
        int current = index - 1;
        while (current > 0
                && parent > 0
                && queue[parent].priority > queue[current].priority) {
            Patient patient = queue[current];
            queue[current] = queue[parent];
            queue[parent] = patient;
            current = parent;
            parent = parent / 2;
        }
    }

    /*
     * remove the frontmost patient, throws exception
     * if the queue is empty
     * parameter: none
     * return: String
     */
    public String dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception("Empty.");
        }
        String patient = queue[1].name;
        queue[1] = queue[index - 1];
        index--;
        bubbleDown(1);

        return patient;
    }
    
    /*
     * bubble down the element if it has low priority
     * parameter: integer position
     * return: none
     */
    private void bubbleDown(int position) {
        int current = position;
        int left = 2 * position;
        int right = 2 * position + 1;
        if (left < index && queue[current].priority > queue[left].priority) {
            current = left;
        }
        else if (left < index && queue[current].priority == queue[left].priority
                && queue[current].name.compareTo(queue[left].name) > 0) {
            current = left;
        }
        if (right < index && queue[current].priority > queue[right].priority) {
            current = right;
        }
        else if (right < index
                && queue[current].priority == queue[right].priority
                && queue[current].name.compareTo(queue[right].name) > 0) {
            current = right;
        }
        if (current != position) {
            Patient patient = queue[current];
            queue[current] = queue[position];
            queue[position] = patient;
            bubbleDown(current);
        }
    }

    /*
     * return the name of the frontmost patient, throws exception
     * if the queue is empty
     * parameter: none
     * return: String
     */
    public String peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("Empty.");
        }
        return queue[1].name;
    }

    /*
     * return the priority of the frontmost patient, throws exception
     * if the queue is empty
     * parameter: none
     * return: Integer
     */
    public int peekPriority() throws Exception {
        if (isEmpty()) {
            throw new Exception("Empty.");
        }
        return queue[1].priority;
    }

    /*
     * modify the priority of a given existing patient
     * parameter: String name, Integer newPriority
     * return: none
     */
    public void changePriority(String name, int newPriority) {
        int position = 0;
        for (int i = 1; i < index; i++) {
            if (queue[i].name.equals(name)) {
                queue[i].priority = newPriority;
                position = i;
                break;
            }
        }
        bubbleUp(position);
    }

    /*
     * return true if the queue is empty
     * parameter: none
     * return: Boolean value
     */
    public boolean isEmpty() {
        if (index == 1) {
            return true;
        }
        return false;
    }

    /*
     * return the size of the queue
     * parameter: none
     * return: Integer
     */
    public int size() {
        return index - 1;
    }

    /*
     * remove all elements from the queue
     * parameter: none
     * return: none
     */
    public void clear() {
        queue = new Patient[MIN_CAPACITY];
        capacity = MIN_CAPACITY;
        index = 1;
    }

    /*
     * return the string representation of the queue
     * parameter: none
     * return: String
     */
    public String toString() {
        String result = "{";
        for (int i = 1; i < index - 1; i++) {
            result += queue[i].name + " (" + queue[i].priority + "), ";
        }
        result += queue[index - 1].name + " (" + queue[index - 1].priority
                + ")}";
        return result;
    }

}
