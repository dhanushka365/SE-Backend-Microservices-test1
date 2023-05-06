package com.service.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {


    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(String studentEmail) {

        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .studentEmail(studentEmail)
                        .isFraudster(false)
                        .createAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
