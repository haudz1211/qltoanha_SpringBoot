package tdh.quanlytoanha;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tdh.quanlytoanha.entities.ServiceEntity;
import tdh.quanlytoanha.repository.IServiceRepository;
import tdh.quanlytoanha.security.entities.Role;
import tdh.quanlytoanha.security.entities.User;
import tdh.quanlytoanha.security.service.IUserService;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class QuanLyToaNhaApplication {
    @Autowired
    private IUserService userService;

    @Autowired
    private IServiceRepository serviceRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @PostConstruct
    public void init() {
        // Kiểm tra userService không phải là null
        if (userService == null) {
            System.out.println("userService is null");
            return;
        }

        List<User> users = userService.findAll();

        // Kiểm tra null trước khi gọi isEmpty()
        if (users == null || users.isEmpty()) {
            User admin = new User();
            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            admin.setFullName("Doan Hau");
            admin.setUsername("admin");
            admin.setPassword("123456");
            admin.setRole(roleAdmin); // Chỉ định một role
            userService.save(admin);
        }

        List<ServiceEntity> requiredServices = serviceRepository.findServiceEntitiesByRequired(1);
        if (requiredServices == null || requiredServices.isEmpty()) {
            ServiceEntity serviceEntity = new ServiceEntity();
            serviceEntity.setName("Vệ sinh");
            serviceEntity.setRequired(1);
            serviceEntity.setPrice(150000);
            serviceEntity.setType("Dịch vụ bắt buộc");
            serviceRepository.save(serviceEntity);

            ServiceEntity serviceEntity1 = new ServiceEntity();
            serviceEntity1.setName("Bảo vệ");
            serviceEntity1.setRequired(1);
            serviceEntity1.setPrice(200000);
            serviceEntity1.setType("Dịch vụ bắt buộc");
            serviceRepository.save(serviceEntity1);
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(QuanLyToaNhaApplication.class, args);
    }
}
