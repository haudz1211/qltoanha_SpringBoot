package tdh.quanlytoanha.controller;

import tdh.quanlytoanha.dtos.BuildingEmployeeDTO;
import tdh.quanlytoanha.dtos.CompanyDTO;
import tdh.quanlytoanha.dtos.SalaryDTO;
import tdh.quanlytoanha.service.building_employee.IBuildingEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/buildingEmployees", produces = "application/json")
public class BuildingEmployeeController {
    @Autowired
    private IBuildingEmployeeService buildingEmployeeService;

    @PostMapping("/create/salaryId={id}")
    public ResponseEntity<?> createNewBuildingEmployeeBySalaryId(@PathVariable Integer id, @RequestBody BuildingEmployeeDTO buildingEmployeeDTO){
        buildingEmployeeService.createNewBuildingEmployeeBySalaryId(id,buildingEmployeeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BuildingEmployeeDTO>> getAllBuildingEmployees() {
        return new ResponseEntity<>(buildingEmployeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingEmployeeDTO> getBuildingEmployeeById(@PathVariable Integer id){
        Optional<BuildingEmployeeDTO> be = buildingEmployeeService.findById(id);
        return be.map(buildingEmployeeDTO -> {
            return new ResponseEntity<>(buildingEmployeeDTO,HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BuildingEmployeeDTO>> getBuildingEmployeeByName(@PathVariable String name){
        List<BuildingEmployeeDTO> buildingEmployeeDTOS = buildingEmployeeService.findBuildingEmployeeByName(name);
        if(buildingEmployeeDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(buildingEmployeeDTOS,HttpStatus.OK);
    }

    @PutMapping("/{empId}/{salaryId}")
    public ResponseEntity<?> updateBuildingEmployee( @PathVariable Integer empId, @PathVariable Integer salaryId, @RequestBody BuildingEmployeeDTO buildingEmployeeDTO){
        buildingEmployeeService.updateBuildingEmployee(empId, salaryId, buildingEmployeeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BuildingEmployeeDTO> deleteBuildingEmployee(@PathVariable Integer id) {
        // Lấy thử đối tượng có id đó ra xem tồn tại chưa để xóa, ko thì trả về status not found
        Optional<BuildingEmployeeDTO> buildingEmployeeDTOOptional = buildingEmployeeService.findById(id);
        return buildingEmployeeDTOOptional.map(buildingEmployeeDTO -> {
            buildingEmployeeService.remove(id);
            return new ResponseEntity<>(buildingEmployeeDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
