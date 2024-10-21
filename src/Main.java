import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();



        String [] cities = main.readData("cities.txt");
//        String [] packages = main.readData("packages.txt");
//        String [] vehicles = main.readData("vehicles.txt");
//        String [] missions = main.readData("missions.txt");

        try {
            CityList cityList = new CityList(new Center(cities[0]));
            for (int i = 1; i < cities.length; i++){
                cityList.addToBack(new Center(cities[i]));
            }

            Center temp = cityList.getSource();
            while (temp != null){
                System.out.println(temp.getName());
                temp = temp.getNext();
            }
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }


        Center Ankara = new Center("Ankara");
        Package p1 = new Package(Ankara, "Package 1");
        Package p2 = new Package(Ankara, "Package 2");
        Package p3 = new Package(Ankara, "Package 3");
        Package p4 = new Package(Ankara, "Package 4");
        Package p5 = new Package(Ankara, "XXX");

        Vehicle a = new Vehicle(Ankara, p1);
        a.push(p2);
        a.push(p3);
        a.push(p4);
        a.push(p5);

        System.out.println(a.pop().getContent());
        System.out.println(a.pop().getContent());
        System.out.println(a.pop().getContent());
        System.out.println(a.pop().getContent());
        System.out.println(a.pop().getContent());

    }
    public String [] readData(String path) { // reads and puts each line in a file to an array.
        String DATA = "";

        try {
            File file = new File(path);

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                DATA += sc.nextLine() + "-";
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("ERROR: Couldn't read file: " + e.getMessage());
        }

        String [] data = DATA.split("-");
        return data;
    }
}