public class Main {
    public static void main(String[] args) {
        int tipoRandom;
       do {
           double tipoRandomDouble = Math.random()*7;
           tipoRandom = (int) tipoRandomDouble;
           System.out.println(tipoRandom);

       }
       while (tipoRandom!=7);


    }

}
