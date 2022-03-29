public class Application {
    public static void main(String[] args) {
        System.out.println("--- APPLICATION STARTED ---");
        Runnable pet = new Pet(100, 100, 100);
        Runnable user = new User((Pet) pet);
        Thread petThread = new Thread(pet);
        Thread userThread = new Thread(user);
        petThread.start();
        userThread.start();
    }
}
