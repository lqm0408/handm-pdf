package com.handm.pdf.controller;

import com.handm.pdf.util.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lqm
 * @date 2024/1/6 17:24
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping(value = "/pdf/{type}", consumes = "multipart/form-data")
    public void toWord(HttpServletResponse response, @PathVariable String type,
                       @RequestParam MultipartFile file) throws IOException {
        FileUtil.transformPdf(response, file, type);
    }

    @PostMapping(value = "/word/{type}", consumes = "multipart/form-data")
    public void toPdf(HttpServletResponse response, @PathVariable String type,
                      @RequestParam MultipartFile file) {
        FileUtil.transformWord(response, file, type);
    }
}
