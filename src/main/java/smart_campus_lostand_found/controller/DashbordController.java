package smart_campus_lostand_found.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smart_campus_lostand_found.service.DashboardService;
import java.util.Map;
@RestController
@RequestMapping("/api/dashboard")
public class DashbordController {
    private final DashboardService service;

    public DashbordController(DashboardService service){
        this.service= service;
    }

    @GetMapping("/summary")
    public Map<String,Long> getSummary(){
        return service.getSummary();
    }

    
}
