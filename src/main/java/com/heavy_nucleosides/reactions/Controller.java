package com.heavy_nucleosides.reactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    ImportService importService;

    @GetMapping("/import")
    String imp() {
        return importService.imp();
    }

    @GetMapping("/import/{filename}")
    String imp_file(@PathVariable String filename) {
        return importService.imp_file(filename);
    }

    @GetMapping("/list")
    List<String> list() {
        return importService.list();
    }
}
