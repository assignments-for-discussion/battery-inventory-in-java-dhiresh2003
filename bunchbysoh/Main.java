package bunchbysoh;

public class Main {
    static class CountsBySoH {
        public int healthy = 0;
        public int exchange = 0;
        public int failed = 0;
    }
    static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
        CountsBySoH counts = new CountsBySoH();

        for (int capacity : presentCapacities) {
            double stateOfHealth = calculateSoH(capacity);

            if (isHealthy(stateOfHealth)) {
                counts.healthy++;
            } else if (isExchange(stateOfHealth)) {
                counts.exchange++;
            } else {
                counts.failed++;
            }
        }
        return counts;
    }
    private static double calculateSoH(int presentCapacity) {
        final int ratedCapacity = 120;
        return 100.0 * presentCapacity / ratedCapacity;
    }
    private static boolean isHealthy(double soh) {
        return soh > 80.0;
    }
    private static boolean isExchange(double soh) {
        return soh >= 63.0 && soh <= 80.0;
    }
    static void testBucketingByHealth() {
        System.out.println("Counting batteries by SoH...\n");
        int[] presentCapacities = {115, 118, 80, 95, 91, 72};
        CountsBySoH counts = countBatteriesByHealth(presentCapacities);
        assert(counts.healthy == 2);
        assert(counts.exchange == 3);
        assert(counts.failed == 1);
        System.out.println("Done counting :)\n");
    }
    // for testing boundary conditions
    static void testBoundaryConditions() {
        System.out.println("Testing boundary conditions...\n");
        int[] presentCapacities = {96, 76};
        CountsBySoH counts = countBatteriesByHealth(presentCapacities);
        assert(counts.healthy == 0);
        assert(counts.exchange == 2);
        assert(counts.failed == 0);
        System.out.println("Boundary conditions tested :)\n");
    }
    public static void main(String[] args) {
        testBucketingByHealth();
        testBoundaryConditions();
    }
}
