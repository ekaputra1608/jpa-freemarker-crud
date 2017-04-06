package com.teguh.jpa.crud.example.controller;

import com.teguh.jpa.crud.example.domain.DepartmentDataGrid;
import com.teguh.jpa.crud.example.domain.DepartmentModel;
import com.teguh.jpa.crud.example.domain.Message;
import com.teguh.jpa.crud.example.entity.Department;
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
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    private DepartmentModel departmentModel;
    @Autowired
    private MessageSource messageSource;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
//        true treats empty string as NULL
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createDepartment(@ModelAttribute("department") @Valid Department department, BindingResult result,
                                   Model model, RedirectAttributes redirectAttributes, Locale locale) {
        model.addAttribute("pageTitle", "New Department - JPA-Crud Project");

        if (result.hasErrors()) {
            model.addAttribute("messages", new Message(Message.ERROR,
                    messageSource.getMessage("validation.field.errors",
                            new Object[]{}, locale)));
            return "department/form";
        }

        try {
            departmentModel.create(department);
            redirectAttributes.addFlashAttribute("messages", new Message(Message.SUCCESS, messageSource.getMessage(
                    "entity.department.CreateSuccess", new Object[]{}, locale)));
        } catch (Exception exc) {
            model.addAttribute("messages", new Message(Message.ERROR, exc.getMessage()));
            return "department/form";
        }

        return "redirect:/department";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createDepartmentForm(Model model) {
        Department entity = new Department();
        model.addAttribute("department", entity);
        model.addAttribute("pageTitle", "New Department - JPA-Crud Project");

        return "department/form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteDepartment(@RequestParam("dept") List<Integer> deptIds, RedirectAttributes redirectAttributes,
                                   Locale locale) {
        try {
            int num = departmentModel.delete(deptIds);
            redirectAttributes.addFlashAttribute("messages", new Message(Message.SUCCESS, messageSource.getMessage(
                    "entity.department.DeleteSuccess", new Object[]{num}, locale)));
        } catch (Exception exc) {
            redirectAttributes.addFlashAttribute("messages", new Message(Message.SUCCESS, messageSource.getMessage(
                    "entity.department.DeleteFailed", new Object[]{}, locale)));
        }

        return "redirect:/department";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayAll(Model model) {
        DepartmentDataGrid dataGrid = new DepartmentDataGrid();
        Page<Department> result = departmentModel.listAll(
                new PageRequest(0, dataGrid.getPageSize(), Sort.Direction.ASC, "deptName"));
        dataGrid.setPageable(result).setSortDir("asc").setSortField("deptName");

        model.addAttribute("dataGrid", dataGrid);
        model.addAttribute("pages", result);
        model.addAttribute("pageTitle", "Department - JPA-Crud Project");

        return "department/list";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String displayAllBinding(DepartmentDataGrid dataGrid, Model model) {
        int page = dataGrid.getPage() - 1;
        int pageSize = dataGrid.getPageSize();
        Page<Department> result = departmentModel.listAll(
                new PageRequest(page, pageSize, dataGrid.getSortSpec("deptName")));
        dataGrid.setPageable(result);
        model.addAttribute("dataGrid", dataGrid);
        model.addAttribute("pages", result);
        model.addAttribute("pageTitle", "Department - JPA-Crud Project");

        return "department/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateDepartment(@ModelAttribute("department") @Valid Department department, BindingResult result, Model model,
                                   RedirectAttributes redirectAttributes, Locale locale) {
        model.addAttribute("pageTitle", "Edit Department - JPA-Crud Project");

        if (result.hasErrors()) {
            model.addAttribute("messages", new Message(Message.ERROR,
                    messageSource.getMessage("validation.field.errors",
                            new Object[]{}, locale)));
            return "department/form";
        }

        try {
            departmentModel.update(department);
            redirectAttributes.addFlashAttribute("messages", new Message(Message.SUCCESS, messageSource.getMessage(
                    "entity.department.UpdateSuccess", new Object[]{}, locale)));
        } catch (Exception exc) {
            model.addAttribute("messages", new Message(Message.ERROR, exc.getMessage()));
            return "department/form";
        }

        return "redirect:/department";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String updateDepartmentForm(@PathVariable("id") Integer id, Model model) {
        Department department = departmentModel.find(id);
        model.addAttribute("department", department);
        model.addAttribute("pageTitle", "Edit Department - JPA-Crud Project");

        return "department/form";
    }

}
