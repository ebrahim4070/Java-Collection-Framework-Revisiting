public class MiddleSquarePRNG {

    // Use volatile because multiple threads modify seed
    private static volatile long seed = 675248; // 6-digit seed

    // Synchronized method to generate next seed
    private static synchronized long nextSeed() {
        long squared = seed * seed;
        String squaredStr = String.format("%012d", squared); // pad with leading zeros to 12 digits
        // Extract middle 6 digits
        String middle = squaredStr.substring(3, 9);
        seed = Long.parseLong(middle);
        return seed;
    }

    // Generate int in range [min, max]
    public static int generate(int min, int max) {
        long num = nextSeed();
        return min + (int)(num % (max - min + 1));
    }

    // Generate float in [min, max)
    public static float generate(float min, float max) {
        long num = nextSeed();
        return min + (num % 1000000) / 1000000.0f * (max - min);
    }

    // Generate double in [min, max)
    public static double generate(double min, double max) {
        long num = nextSeed();
        return min + (num % 1000000) / 1000000.0 * (max - min);
    }

    // Thread class to generate random numbers of a type
    static class RandomThread extends Thread {
        String type;
        public RandomThread(String type) {
            this.type = type;
        }
        @Override
        public void run() {
            System.out.println("Thread [" + type + "] started:");
            for (int i = 0; i < 5; i++) {
                switch (type) {
                    case "int" -> System.out.println("[int] " + generate(1, 100));
                    case "float" -> System.out.println("[float] " + generate(1.0f, 10.0f));
                    case "double" -> System.out.println("[double] " + generate(1.0, 10.0));
                }
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
            System.out.println("Thread [" + type + "] finished.");
        }
    }

    // Main method to start threads
    public static void main(String[] args) {
        Thread t1 = new RandomThread("int");
        Thread t2 = new RandomThread("float");
        Thread t3 = new RandomThread("double");

        t1.start();
        t2.start();
        t3.start();
    }
}
