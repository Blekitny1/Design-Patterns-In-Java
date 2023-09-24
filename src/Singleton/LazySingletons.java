package Singleton;

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("Initializing a lazy singleton");
    }

//    public static synchronized LazySingleton getInstance() { //slow
//        if (instance == null) { //we initialize only if someone calls an instance
//            instance = new LazySingleton(); //potentially thread unsafe => can call constructor more than once
//        }
//        return instance;
//    }

    // double-checked locking (outdated, but good to know this textbook implementation)

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton(); // both lazy and thread safe!
                }
            }
        }
        return instance;
    }


}

public class LazySingletons {
}
