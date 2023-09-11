package lk.carhire;

import lk.carhire.entity.CategoryEntity;
import lk.carhire.util.SessionFactoryConfiguration;
import org.hibernate.Session;

public class LauncherWrapper {
    public static void main(String[] args) throws Exception {
       // Launcher.main(args);
       Session session = SessionFactoryConfiguration.getInstance().getSession();
       /*
        CategoryEntity categoryEntity = session.get(CategoryEntity.class,1001);
        System.out.println(categoryEntity.toString());

       */
    }
}
