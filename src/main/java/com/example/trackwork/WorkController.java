package com.example.trackwork;

import java.util.List;
import java.util.UUID;

import com.example.trackwork.model.Work;
import com.example.trackwork.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//mark class as Controller
@RestController
public class WorkController {

    //autowire the WorkService class
    @Autowired
    WorkService workService;

    //creating a get mapping that retrieves all the work detail from the database
    @CrossOrigin
    @GetMapping("/work")
    private List<Work> getAllWork() {
        boolean filter = SecurityContextHolder.getContext().getAuthentication().getName().equalsIgnoreCase("rais")?false:true;
        return workService.getAllWorks(filter);
    }

    //creating a get mapping that retrieves all the work detail from the database
    @CrossOrigin
    @GetMapping("/work/client/{clientName}")
    private List<Work> getAllWorkByClientId(@PathVariable("clientName") String clientName) {
        return workService.getWorkByClient(clientName);
    }

    //creating a get mapping that retrieves the detail of a specific work
    @CrossOrigin
    @GetMapping("/work/{workid}")
    private Work getWork(@PathVariable("workid") String workid) {
        Work work = workService.getWorkById(workid);
        System.out.println(work);
        return work;
    }

    //creating a delete mapping that deletes a specified work
    @CrossOrigin
    @DeleteMapping("/work/{workid}")
    private void deleteWork(@PathVariable("workid") String workid) {
        workService.delete(workid);
    }

    //creating post mapping that post the work detail in the database
    @CrossOrigin
    @PostMapping("/work")
    private String saveWork(@RequestBody Work work) {
        if(StringUtils.isEmpty(work.getId())) {
            work.setId(UUID.randomUUID().toString());
        }
        workService.saveOrUpdate(work);
        return work.getId();
    }

    //creating put mapping that updates the work detail
    @CrossOrigin
    @PutMapping("/work/{workid}")
    private Work update(@RequestBody Work work, @PathVariable("workid")  String workid) {

        workService.saveOrUpdate(work);
        return work;
    }

}
