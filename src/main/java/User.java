import java.util.Scanner;

public class User implements Runnable{

    private Pet pet;
    public User(Pet pet){
        this.pet = pet;
    }

    Scanner userInput = new Scanner(System.in);
    @Override
    public void run() {
        while (pet.isAlive()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (UserInput.getInput()){
                case 1 -> pet.feed();
                case 2 -> pet.play();
            }
        }

    }

    private class UserInput{
        static Scanner scanner = new Scanner(System.in);

        static int getInput(){
            return scanner.hasNext() ? scanner.nextInt() : -1;
        }
    }
}
