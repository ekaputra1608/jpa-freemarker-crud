package com.teguh.jpa.crud.example.controller;

import com.teguh.jpa.crud.example.domain.EmployeeDataGrid;
import com.teguh.jpa.crud.example.domain.Message;
import com.teguh.jpa.crud.example.domain.PersonModel;
import com.teguh.jpa.crud.example.entity.Department;
import com.teguh.jpa.crud.example.entity.Person;
import com.teguh.jpa.crud.example.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private PersonModel personModel;
    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
//        true treats empty string as NULL
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult result, Model model,
                               RedirectAttributes redirectAttributes, Locale locale) {
        model.addAttribute("pageTitle", "New Employee - JPA-Crud Project");

        if (result.hasErrors()) {
            model.addAttribute("messages", new Message(Message.ERROR,
                    messageSource.getMessage("validation.field.errors",
                            new Object[]{}, locale)));
            return "employee/form";
        }

        try {
            personModel.create(person);
            redirectAttributes.addFlashAttribute("messages", new Message(Message.SUCCESS, messageSource.getMessage(
                    "entity.person.CreateSuccess", new Object[]{}, locale)));
        } catch (Exception exc) {
            model.addAttribute("messages", new Message(Message.ERROR, exc.getMessage()));
            return "employee/form";
        }

        return "redirect:/employee";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPersonForm(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        model.addAttribute("gender", person);
        model.addAttribute("pageTitle", "New Employee - JPA-Crud Project");

        return "employee/form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePerson(@RequestParam("pid") List<Long> personIds, RedirectAttributes redirectAttributes,
                               Locale locale) {
        try {
            int num = personModel.delete(personIds);
            redirectAttributes.addFlashAttribute("messages", new Message(Message.SUCCESS, messageSource.getMessage(
                    "entity.person.DeleteSuccess", new Object[]{num}, locale)));
        } catch (Exception exc) {
            redirectAttributes.addFlashAttribute("messages", new Message(Message.SUCCESS, messageSource.getMessage(
                    "entity.person.DeleteFailed", new Object[]{}, locale)));
        }

        return "redirect:/employee";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayAll(Model model) {
        EmployeeDataGrid dataGrid = new EmployeeDataGrid();
        Page<Person> result = personModel.listAll(
                new PageRequest(0, dataGrid.getPageSize(), Sort.Direction.ASC, "fullname"));
        dataGrid.setPageable(result).setSortDir("asc").setSortField("fullname");

        model.addAttribute("dataGrid", dataGrid);
        model.addAttribute("pages", result);
        model.addAttribute("pageTitle", "Employee - JPA-Crud Project");

        return "employee/list";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String displayAllBinding(EmployeeDataGrid employeeGrid, Model model) {
        int page = employeeGrid.getPage() - 1;
        int pageSize = employeeGrid.getPageSize();

        PageRequest paging = new PageRequest(page, pageSize, employeeGrid.getSortSpec("fullname"));
        Page<Person> result = personModel.findAllByCriteria(employeeGrid.getDepartmentId(), employeeGrid.getTerm(),
                paging);
        employeeGrid.setPageable(result);
        model.addAttribute("dataGrid", employeeGrid);
        model.addAttribute("pages", result);
        model.addAttribute("pageTitle", "Employee - JPA-Crud Project");

        return "employee/list";
    }

    @ModelAttribute(value = "departments")
    public Iterable<Department> listDepartments() {
        return departmentRepository.findAll(new Sort(Sort.Direction.ASC, "deptName"));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updatePerson(@ModelAttribute("person") @Valid Person person, BindingResult result, Model model,
                               RedirectAttributes redirectAttributes, Locale locale) {
        model.addAttribute("pageTitle", "Edit Employee - JPA-Crud Project");

        if (result.hasErrors()) {
            model.addAttribute("messages", new Message(Message.ERROR,
                    messageSource.getMessage("validation.field.errors",
                            new Object[]{}, locale)));
            return "employee/form";
        }

        try {
            personModel.update(person);
            redirectAttributes.addFlashAttribute("messages", new Message(Message.SUCCESS, messageSource.getMessage(
                    "entity.person.UpdateSuccess", new Object[]{}, locale)));
        } catch (Exception exc) {
            model.addAttribute("messages", new Message(Message.ERROR, exc.getMessage()));
            return "employee/form";
        }

        return "redirect:/employee";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String updatePersonForm(@PathVariable("id") Long id, Model model) {
        Person person = personModel.find(id);
        model.addAttribute("person", person);
        model.addAttribute("pageTitle", "Edit Employe - JPA-Crud Project");

        return "employee/form";
    }

}
