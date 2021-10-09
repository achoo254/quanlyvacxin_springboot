package com.quanly.demo.api;

import com.quanly.demo.model.Notify;
import com.quanly.demo.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/notify")
public class NotifyController {
    @Autowired
    NotifyService notifyService;

    @PostMapping(value = "/post/")
    public Notify save(@Valid @RequestBody Notify notify) {
        return notifyService.save(notify);
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<Notify> putNotify(@PathVariable(value = "id") int notifyId,
                                            @Valid @RequestBody Notify form) {
        Notify notify = notifyService.getOne(notifyId);
        if (notify == null) {
            return ResponseEntity.notFound().build();
        }

        notify.setStatus(form.isStatus());

        Notify updatedNotify = notifyService.save(notify);
        return ResponseEntity.ok(updatedNotify);
    }
}
