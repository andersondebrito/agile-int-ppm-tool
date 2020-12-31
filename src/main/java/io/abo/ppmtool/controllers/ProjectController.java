package io.abo.ppmtool.controllers;

import io.abo.ppmtool.domain.Project;
import io.abo.ppmtool.services.MapValidationErrorService;
import io.abo.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    MapValidationErrorService errorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = errorService.mapValidationError(result);
        if(errorMap != null) return errorMap;

        Project projectSaved = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(projectSaved, HttpStatus.CREATED);
    }
}
