package pro.sky.HomeWorkEmployeeLibrary.ServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.HomeWorkEmployeeLibrary.Exception.BadParamsException;
import pro.sky.HomeWorkEmployeeLibrary.Exception.EmployeeAlreadyAddedException;
import pro.sky.HomeWorkEmployeeLibrary.Exception.EmployeeNotFoundException;
import pro.sky.HomeWorkEmployeeLibrary.Exception.EmployeeStorageIsFullException;
import pro.sky.HomeWorkEmployeeLibrary.Interface.EmployeeInterface;
import pro.sky.HomeWorkEmployeeLibrary.Model.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeInterface {

    public final int NUMBER_OF_EMPLOYEES = 10;

    private final List<Employee> employeesList;

    public EmployeeServiceImpl() {
        this.employeesList = new ArrayList<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employeesList.size() > NUMBER_OF_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников");
        }

        if (employeesList.contains(employee)) {
            throw new EmployeeAlreadyAddedException("уже есть такой сотрудник");
        }

        if (StringUtils.containsAny(firstName, "0123456789") || (StringUtils.containsAny(firstName, "@#$%^&*\\//"))) {
            throw new BadParamsException("недопустимые символы");
        }

        if (StringUtils.containsAny(lastName, "0123456789") || (StringUtils.containsAny(lastName, "@#$%^&*\\//"))) {
            throw new BadParamsException("недопустимые символы");
        }
        employeesList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeesList.contains(employee)) {
            employeesList.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeesList.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableList(employeesList);
    }
}
