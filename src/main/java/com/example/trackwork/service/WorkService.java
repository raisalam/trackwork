package com.example.trackwork.service;


import com.example.trackwork.model.Work;
import com.example.trackwork.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//defining the business logic
@Service
public class WorkService {
    @Autowired
    WorkRepository workRepository;

    //getting all work record by using the method findaAll() of CrudRepository
    public List<Work> getAllWorks(boolean isFilter) {
        List<Work> works = new ArrayList<>();
        workRepository.findAll().forEach(work1 -> {
            if (isFilter){
                if(!work1.getClientName().equalsIgnoreCase("Bhuvan") && !work1.getStatus().equalsIgnoreCase("paid")) {
                    works.add(work1);
                }
            } else {
                works.add(work1);
            }
        });
       // Collections.sort(works, Comparator.comparing(Work::getDate));
       // Collections.sort(works, (emp1, emp2) -> emp2.getDate().compareTo(emp1.getDate()));
        return works;
    }

    //getting a specific record by using the method findById() of CrudRepository
    public Work getWorkById(String id) {
        return workRepository.findById(id).get();
    }
    //getting a specific record by using the method findById() of CrudRepository
    public List<Work> getWorkByClient(String clientName) {
        return  workRepository.findAll().stream().filter(p -> p.getClientName().equalsIgnoreCase(clientName)).
                collect(Collectors.toList());
    }

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Work work) {
        workRepository.save(work);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(String id) {
        workRepository.deleteById(id);
    }

    //updating a record
    public void update(Work work, String workid) {
        workRepository.save(work);
    }
}