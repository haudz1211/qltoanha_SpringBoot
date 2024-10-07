package tdh.quanlytoanha.service.company_employee;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tdh.quanlytoanha.dtos.CompanyEmployeeDTO;
import tdh.quanlytoanha.entities.CompanyEmployeeEntity;
import tdh.quanlytoanha.repository.ICompanyEmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyEmployeeService implements ICompanyEmployeeService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ICompanyEmployeeRepository companyEmployeeRepository;

    @Override
    public List<CompanyEmployeeDTO> findAll() {
        List<CompanyEmployeeEntity> companyEmployeeEntities = companyEmployeeRepository.findAll();
        return companyEmployeeEntities.stream()
                .map(companyEmployeeEntity -> modelMapper.map(companyEmployeeEntity, CompanyEmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CompanyEmployeeDTO> findById(Integer id) {
        Optional<CompanyEmployeeEntity> companyEmployeeEntity = companyEmployeeRepository.findById(id);
        return companyEmployeeEntity
                .map(entity -> modelMapper.map(entity, CompanyEmployeeDTO.class));
    }

    @Override
    public CompanyEmployeeDTO save(CompanyEmployeeDTO companyEmployeeDTO) {
        CompanyEmployeeEntity companyEmployeeEntity = modelMapper.map(companyEmployeeDTO, CompanyEmployeeEntity.class);
        CompanyEmployeeEntity updatedEntity = companyEmployeeRepository.save(companyEmployeeEntity);
        return modelMapper.map(updatedEntity, CompanyEmployeeDTO.class);
    }

    @Override
    public void remove(Integer id) {
        if (!companyEmployeeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        companyEmployeeRepository.deleteById(id);
    }

    @Override
    public List<CompanyEmployeeDTO> findAllEmployeeOfCompany(Integer id) {
        List<CompanyEmployeeEntity> companyEmployeeEntities = companyEmployeeRepository.getCompanyEmployeeEntitiesByCompany_Id(id);
        return companyEmployeeEntities.stream()
                .map(companyEmployeeEntity -> modelMapper.map(companyEmployeeEntity, CompanyEmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public int countCompanyEmployeesByCompanyID(Integer companyId) {
        return companyEmployeeRepository.countCompanyEmployeeEntitiesByCompany_Id(companyId);
    }

    @Override
    public List<CompanyEmployeeDTO> findEmployeesByNameAndCompanyId(String empName, Integer companyId) {
        return companyEmployeeRepository.getCompanyEmployeeEntitiesByNameAndCompany_Id(empName, companyId)
                .stream()
                .map(companyEmployee -> modelMapper.map(companyEmployee, CompanyEmployeeDTO.class))
                .collect(Collectors.toList());
    }
}
