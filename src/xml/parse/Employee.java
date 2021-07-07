package xml.parse;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private long id;
    private String first_name;
    private String last_name;
    private BigDecimal salary;
    private LocalDate hire_date;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", salary=" + salary +
                ", hire_date=" + hire_date +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    public Employee() {
    }

    public Employee(long id, String first_name, String last_name, BigDecimal salary, LocalDate hire_date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.salary = salary;
        this.hire_date = hire_date;
    }
}
