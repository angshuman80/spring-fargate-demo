package karate;

import com.demo.app.AppApplication;
import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {AppApplication.class})
public class EmpoyeeKarateTest {

    @Karate.Test
    Karate dummyTest() {
        return Karate.run("classpath:karate/dummy.feature");
    }

    @Karate.Test
    Karate createAndGetEmployeeTest() {
        return Karate.run("classpath:karate/employeePost.feature");
    }

    @Karate.Test
    Karate createAndGetSingleEmployeeTest() {
        return Karate.run("classpath:karate/employeeSingle.feature");
    }

  //  @Karate.Test
   // Karate employeeTest() {
    //    return Karate.run("classpath:karate/employeeGET.feature");
    //}



}
