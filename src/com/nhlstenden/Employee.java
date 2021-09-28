package com.nhlstenden;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 * This class holds all the information about an employee.
 *
 * @author NHL Stenden - Jan Doornbos
 * @version 1.0
 * @since 1.0
 */
public class Employee
{
    private int number;
    private LocalDate dateOfBirth;
    private LocalDate enlisted;

    private final DateTimeFormatter dateTimeFormatter;

    // Constructor

    /**
     * Create a new employee with a number, date of birth and the date the employee started working for the company.
     *
     * @param number The employee number.
     * @param dateOfBirth The date of birth, formatted as ddMMyyyy.
     * @param enlisted The date of the employee joining the company, formatted as ddMMyyyy.
     */
    public Employee(int number, String dateOfBirth, String enlisted)
    {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");

        this.number = number;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, this.dateTimeFormatter);
        this.enlisted = LocalDate.parse(enlisted, this.dateTimeFormatter);
    }

    // Getters & Setters

    public int getNumber()
    {
        return this.number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public LocalDate getDateOfBirth()
    {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = LocalDate.parse(dateOfBirth, this.dateTimeFormatter);
    }

    public LocalDate getEnlisted()
    {
        return this.enlisted;
    }

    public void setEnlisted(LocalDate enlisted)
    {
        this.enlisted = enlisted;
    }

    public void setEnlisted(String enlisted)
    {
        this.enlisted = LocalDate.parse(enlisted, this.dateTimeFormatter);
    }

    // Custom methods

    /**
     * Get the age of an employee.
     *
     * @return The age the of the employee.
     */
    public int getAge()
    {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Get the amount of years the employee is working for the company.
     *
     * @return The amount of years the employee is working for the company.
     */
    public int getAmountOfYearsEnlisted()
    {
        return Period.between(this.enlisted, LocalDate.now()).getYears();
    }

    /**
     * Every employee has a basic amount of holiday days. The employees of department one get 25 days. All the
     * employees on other departments get 20 days.
     *
     * @return The amount of basic holiday days the employee gets.
     */
    private int getBasicAmountOfHolidayDays()
    {
        if (this.getDepartment() == 1)
        {
            return 25;
        }
        else
        {
            return 20;
        }
    }

    /**
     * Some employees get extra holiday days. Employees with an age above 55 gain five more extra days.
     * Employees that are working for more than ten years for the company gain another extra three days.
     *
     * @return The amount of extra holiday days.
     */
    private int getExtraAmountOfHolidayDays()
    {
        int amountOfDays = 0;

        if (this.getAge() > 55)
        {
            amountOfDays += 5;
        }

        if (this.getAmountOfYearsEnlisted() > 10)
        {
            amountOfDays += 3;
        }

        return amountOfDays;
    }

    /**
     * Get the amount of holiday days the employee deserves.
     *
     * @return The amount of holiday days.
     */
    public int getAmountOfHolidayDays()
    {
        return this.getBasicAmountOfHolidayDays() + this.getExtraAmountOfHolidayDays();
    }

    /**
     * Get all the individual numbers from an {@code int}.
     *
     * @param number The {@code int} that needs to be split.
     * @return A {@code LinkedList} of all the individual numbers.
     */
    private LinkedList<Integer> getIndividualNumbers(int number)
    {
        LinkedList<Integer> stack = new LinkedList<>();
        while (number > 0)
        {
            stack.push(number % 10);
            number = number / 10;
        }

        return stack;
    }

    /**
     * Get the department the employee is working on.
     *
     * @return The department number. It returns -1 if it fails to parse the employee number.
     */
    public int getDepartment()
    {
        LinkedList<Integer> individualNumbers = this.getIndividualNumbers(this.number);
        if (individualNumbers.size() < 4)
        {
            return -1;
        }

        return this.getIndividualNumbers(this.number).get(0);
    }
}
