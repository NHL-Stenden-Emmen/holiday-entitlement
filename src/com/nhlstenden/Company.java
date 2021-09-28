package com.nhlstenden;

import java.util.Comparator;
import java.util.HashSet;

/**
 * This class registers the employees that are working for the company.
 *
 * @author NHL Stenden - Jan Doornbos
 * @version 1.0
 * @since 1.0
 */
public class Company
{
    private final HashSet<Employee> employees;

    /**
     * Create a new empty company.
     */
    public Company()
    {
        this.employees = new HashSet<>();
    }

    public HashSet<Employee> getEmployees()
    {
        return this.employees;
    }

    public void addEmployee(Employee employee)
    {
        this.employees.add(employee);
    }

    /**
     * Get the total amount of holiday days the company has to pay.
     *
     * @return The total amount of holiday days.
     */
    public int getTotalAmountOfHolidayDays()
    {
        int total = 0;

        for (Employee employee : this.employees)
        {
            total += employee.getAmountOfHolidayDays();
        }

        return total;
    }

    /**
     * Get the oldest employee of the company.
     *
     * @return The oldest employee of the company. It returns {@code null} when there are no employees found.
     */
    public Employee getOldestEmployee()
    {
        return this.employees
                .stream()
                .max(Comparator.comparing(Employee::getAge))
                .orElse(null);
    }

    /**
     * Get the average of serving years.
     *
     * @return The average of serving years. It returns {@code -1.0} when there are no employees found.
     */
    public double averageAmountOfServingYears()
    {
        return this.employees
                .stream()
                .mapToDouble(Employee::getAmountOfYearsEnlisted)
                .average()
                .orElse(-1.0);
    }
}
