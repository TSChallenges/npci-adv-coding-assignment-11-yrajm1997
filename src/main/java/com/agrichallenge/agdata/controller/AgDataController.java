package com.agrichallenge.agdata.controller;

import com.agrichallenge.agdata.model.AgData;
import com.agrichallenge.agdata.service.AgDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/agdata")
public class AgDataController {

    @Autowired
    private AgDataService agDataService;

    @GetMapping("")
    public ResponseEntity<List<AgData>> getAllProducts() throws IOException {
        List<AgData> products = agDataService.loadAgData();
        if (products.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/crop-count")
    public ResponseEntity<Long> getCropCount(@RequestParam String cropName) {
        Long count = agDataService.getCropCount(cropName);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/average-yield")
    public ResponseEntity<Double> getAverageYield(@RequestParam String cropName) {
        double averageYield = agDataService.getAverageYield(cropName);
        return new ResponseEntity<>(averageYield, HttpStatus.OK);
    }

    @GetMapping("/by-region")
    public ResponseEntity<List<AgData>> getDataByRegion(@RequestParam String region) {
        List<AgData> dataByRegion = agDataService.getRecordsByRegion(region);
        if (dataByRegion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dataByRegion, HttpStatus.OK);
    }
}
