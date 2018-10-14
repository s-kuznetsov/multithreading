public class Main {
    public static void main(String[] args) {
        Handler handler = new Handler();

        new Thread(()-> {
            while (true) {
                handler.addNewNumber();
            }
        }).start();

        new Thread(()-> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.showNumberAndDeleteIt();
            }
        }).start();
    }
}
