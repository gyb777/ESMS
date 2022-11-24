package com.scujj.task;


import com.scujj.API.UpdateRiskArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class UpdataRiskAreaTask {
    @Autowired
    UpdateRiskArea updateRiskArea;

    @Scheduled(cron = "0 0 0 * * ? ")
    public void startTask() {
        updateRiskArea.updateDataBase();
    }
}
