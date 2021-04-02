import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration con = new Configuration().configure("hibernate.cfg.xml");
        System.out.println("Maven Project!");
    }
}
//proposal & section