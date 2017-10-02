package io.swagger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.Geode;
import io.swagger.model.Employee;
import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" })
public class Swagger2SpringBoot implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
       
        try {            
            Geode cache = new Geode();
            
            //Populate initial employees
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jp = jsonFactory.createParser(new File("C:/employees_list.json"));

            ObjectMapper objectMapper = new ObjectMapper();

            MappingIterator<Employee> iter = objectMapper.readValues(jp, Employee.class);

            while (iter.hasNext()) {
                Employee e = iter.next();
                ObjectMapper om = new ObjectMapper();
                String value = om.writeValueAsString(e);
                System.out.println(value);

                //put into IMDG
                cache.getRegion().put(e.getId().toString(), value);
            }

            System.out.println("main: employees upload was finished successfuly");
        }catch (Exception e) {
            System.out.println("main: exception " + e.getMessage());
        }
        
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
