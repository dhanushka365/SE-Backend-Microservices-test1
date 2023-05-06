package com.service.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {
    private final FraudCheckService fraudCheckService;


    @GetMapping(path = "{studentEmail}")
    public FraudCheckResponse isFraudster(
        @PathVariable("studentEmail")  String studentEmail){
    boolean isFraudulentStudent = fraudCheckService.isFraudulentCustomer(studentEmail);
    log.info("fraud check request for student { }", studentEmail);
    return new FraudCheckResponse(isFraudulentStudent);

    }
}
