package com.treeleaf.treeleafvehicletracking;

import com.treeleaf.treeleafvehicletracking.entity.*;
import com.treeleaf.treeleafvehicletracking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class TreeleafVehicleTrackingApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMovementRepository vehicleMovementRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(TreeleafVehicleTrackingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role r1 = new Role("ROLE_ADMIN");
        Role r2 = new Role("ROLE_USER");

        List<Role> roles = Arrays.asList(r1, r2);
        roleRepository.saveAll(roles);

        Set<Role> roles1 = new HashSet<>();
        roles1.add(r1);

        Set<Role> roles2 = new HashSet<>();
        roles2.add(r2);

        Set<Role> roles3 = new HashSet<>();
        roles3.add(r1);
        roles3.add(r2);


        User user1 = new User("admin",passwordEncoder.encode("admin"),true);
        User user2 = new User("patrick",passwordEncoder.encode("patrick"),true);
        User user3 = new User("sponge",passwordEncoder.encode("sponge"),true);
        User user4 = new User("chum",passwordEncoder.encode("chum"),true);



        userRepository.save(user1);
        user1.setRoles(roles1);
        userRepository.save(user1);



        userRepository.save(user2);
        user2.setRoles(roles2);
        userRepository.save(user2);

        userRepository.save(user3);
        user3.setRoles(roles3);
        userRepository.save(user3);

        userRepository.save(user4);
        user4.setRoles(roles3);
        userRepository.save(user4);


        Camera camera1 = new Camera("SONY","S2","polecam");
        Camera camera2 = new Camera("SONY","S3","polecam");
        Camera camera3 = new Camera("SONY","A2","polecam");
        Camera camera4 = new Camera("SONY","Z2","polecam");
        Camera camera5 = new Camera("SONY","A3","polecam");
        Camera camera6 = new Camera("Samsung","7Z","polecam");

        cameraRepository.saveAll(Arrays.asList(camera1,camera2,camera3,camera4,camera5,camera6));

        Location location1 = new Location("Sundarijal","3","NEPAL");
        Location location2 = new Location("Gokarna","3","NEPAL");
        Location location3 = new Location("Jorpati","3","NEPAL");
        Location location4 = new Location("Boudha","3","NEPAL");

        List<Location> locations = Arrays.asList(location1, location2, location3, location4);
        locationRepository.saveAll(locations);

        location1.addCamera(camera1);
        location1.addCamera(camera2);

        location2.addCamera(camera3);
        location2.addCamera(camera4);

        location3.addCamera(camera5);
        location3.addCamera(camera6);

        location4.addCamera(camera1);
        location4.addCamera(camera3);

        locations = Arrays.asList(location1, location2, location3, location4);
        locationRepository.saveAll(locations);

        Vehicle vehicle1 = new Vehicle("Honda DIO","BA 1255","Scooter");
        Vehicle vehicle2 = new Vehicle("Honda Aviator","BA 2344","Scooter");
        Vehicle vehicle3 = new Vehicle("Pulsar 150","PA 1255","Bike");
        Vehicle vehicle4 = new Vehicle("TVS","CHA 1255","Bike");
        Vehicle vehicle5 = new Vehicle("Tata sumo","UBA 1255","Jeep");
        Vehicle vehicle6 = new Vehicle("Eicher Truck","CBA 1255","Truck");
        Vehicle vehicle7 = new Vehicle("Yamraj Truck","DBA 1255","Truck");

        vehicleRepository.saveAll(Arrays.asList(vehicle1,vehicle2,vehicle3,vehicle4,vehicle5,vehicle6,vehicle7));

        VehicleMovement vm1 = new VehicleMovement(camera1,location4,vehicle1);
        VehicleMovement vm2 = new VehicleMovement(camera2,location1,vehicle2);
        VehicleMovement vm3 = new VehicleMovement(camera3,location2,vehicle3);
        VehicleMovement vm4 = new VehicleMovement(camera4,location2,vehicle4);
        VehicleMovement vm5 = new VehicleMovement(camera5,location3,vehicle5);
        VehicleMovement vm6 = new VehicleMovement(camera6,location3,vehicle6);
        VehicleMovement vm7 = new VehicleMovement(camera1,location4,vehicle7);
        VehicleMovement vm8 = new VehicleMovement(camera4,location4,vehicle1);
        VehicleMovement vm9 = new VehicleMovement(camera5,location3,vehicle1);
        VehicleMovement vm10 = new VehicleMovement(camera6,location3,vehicle2);
        VehicleMovement vm11 = new VehicleMovement(camera2,location1,vehicle1);


        vehicleMovementRepository.saveAll(Arrays.asList(vm1,vm2,vm3,vm4,vm5,vm6,vm7,vm8,vm9,vm10,vm11));

    }
}
