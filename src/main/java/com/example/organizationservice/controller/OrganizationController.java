package com.example.organizationservice.controller;

import com.example.organizationservice.model.Organization;
import com.example.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/organizations")
public class OrganizationController{

    private OrganizationService orgService;

    @Autowired
    public OrganizationController(OrganizationService orgService){
        this.orgService = orgService;
    }

    @GetMapping("/{organizationId}")
    ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") String organizationId) {
        Optional<Organization> optionalOrganization = orgService.getOrg(organizationId);
        if (optionalOrganization.isPresent()) {
            return ResponseEntity.ok(optionalOrganization.get());
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping( "/{organizationId}")
    public void updateOrganization(@PathVariable("organizationId") String orgId, @RequestBody Organization org) {
        orgService.updateOrg(org);
    }

    @PostMapping("/{organizationId}")
    public void saveOrganization(@RequestBody Organization org) {
        orgService.saveOrg(org);
    }

    @DeleteMapping("/{organizationId}")
    public void deleteOrganization(@PathVariable("orgId") String orgId, @RequestBody Organization org) {
        orgService.deleteOrg(org);
    }
}
