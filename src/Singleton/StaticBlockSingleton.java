package Singleton;

import java.io.File;
import java.io.IOException;

class StaticBlockSingleton2 {
    private StaticBlockSingleton2() throws IOException {
        System.out.println("Singleton is initializing");
        File.createTempFile(".", ".");
    }

    private static StaticBlockSingleton2 instance;

    static { // treat this as a static constructor, where we can safely do everything connected to creation
        try {
            instance = new StaticBlockSingleton2();
        } catch (Exception e) {
            System.err.println("Failed to create singleton");
        }
    }

    public static StaticBlockSingleton2 getInstance() {
        return instance;
    }
}

public class StaticBlockSingleton {
}
